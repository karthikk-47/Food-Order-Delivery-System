$recoveredDir = Join-Path $PSScriptRoot "..\recovered-src"
$srcOut = Join-Path $PSScriptRoot "..\recovered-src-src"
if (-not (Test-Path $srcOut)) { New-Item -ItemType Directory -Path $srcOut | Out-Null }

function Split-Args($args) {
    $list = @()
    $sb = ""
    $depth = 0
    for ($i=0; $i -lt $args.Length; $i++) {
        $c = $args[$i]
        if ($c -eq '<') { $depth++ ; $sb += $c; continue }
        if ($c -eq '>') { if ($depth -gt 0) { $depth-- } ; $sb += $c; continue }
        if ($c -eq ',' -and $depth -eq 0) { $list += $sb.Trim(); $sb = ""; continue }
        $sb += $c
    }
    if ($sb.Trim()) { $list += $sb.Trim() }
    return $list
}

Get-ChildItem -Path $recoveredDir -Filter *.javap.txt | ForEach-Object {
    # read raw bytes and detect encoding
    $bytes = [System.IO.File]::ReadAllBytes($_.FullName)
    if ($bytes.Length -ge 2 -and $bytes[0] -eq 0xFF -and $bytes[1] -eq 0xFE) {
        $text = [System.Text.Encoding]::Unicode.GetString($bytes)
    } elseif ($bytes.Length -ge 2 -and $bytes[0] -eq 0xFE -and $bytes[1] -eq 0xFF) {
        $text = [System.Text.Encoding]::BigEndianUnicode.GetString($bytes)
    } else {
        try { $text = [System.Text.Encoding]::UTF8.GetString($bytes) } catch { $text = [System.Text.Encoding]::Default.GetString($bytes) }
    }
    # strip NULs that come from mis-decoded UTF-16
    $text = $text -replace "`0",""

    # find first occurrence of class/interface/enum declaration with fully-qualified name
    if ($text -match "\b(class|interface|enum)\b\s+([\w\.$]+)") {
        $fullClass = $matches[2]
        # derive package and class name
        $parts = $fullClass -split '\\.'
        if ($parts.Length -gt 1) {
            $pkgName = ($parts[0..($parts.Length-2)] -join '.')
        } else { $pkgName = "" }
        $classNamePart = $parts[-1]
        # handle inner classes by replacing $ with _ for filename and class name
        $className = $classNamePart -replace '\$','_'
        $outDir = if ($pkgName) { Join-Path $srcOut ($pkgName -replace '\.','\\') } else { $srcOut }
        if (-not (Test-Path $outDir)) { New-Item -ItemType Directory -Path $outDir -Force | Out-Null }
        $outFile = Join-Path $outDir ($className + ".java")

        $sb = @()
        if ($pkgName) { $sb += "package $pkgName;"; $sb += "" }
        $sb += "public class $className {"

        # find method/constructor lines (simple heuristic: lines starting with visibility)
        $lines = $text -split "`n"
        foreach ($line in $lines) {
            $m = $line.Trim()
            # constructor
            if ($m -match "^(public|protected|private)\s+" + [regex]::Escape($classNamePart) + "\((.*)\);$") {
                $argsRaw = $matches[2]
                $argsArr = @()
                if ($argsRaw.Trim()) { $split = Split-Args $argsRaw ; $idx=0; foreach ($a in $split) { $argsArr += ("Object arg$idx") ; $idx++ } }
                $argsOut = $argsArr -join ", "
                $sb += "    public $className($argsOut) { }"
            }
            # methods: capture return type (non-greedy), name and args
            elseif ($m -match "^(public|protected|private)\s+(.+?)\s+(\w+)\((.*)\);$") {
                $visibility = $matches[1]
                $retType = $matches[2]
                $mname = $matches[3]
                $argsRaw = $matches[4]
                $argsArr = @()
                if ($argsRaw.Trim()) { $split = Split-Args $argsRaw ; $idx=0; foreach ($a in $split) { $atype = $a.Trim(); # use original type (fully-qualified/generic)
                        # sanitize common descriptors
                        $atype = $atype -replace '\s+',' '
                        $argsArr += ("$atype arg$idx") ; $idx++ } }
                $argsOut = $argsArr -join ", "
                $body = "{ return null; }"
                if ($retType -eq "void") { $body = "{ }" }
                # sanitize return type: keep as-is
                $sb += "    $visibility $retType $mname($argsOut) $body"
            }
        }

        $sb += "}"
        $sb | Set-Content -Path $outFile -Encoding UTF8
        Write-Host "Wrote $outFile"
    } else {
        Write-Host "No class declaration found in $($_.Name)"
    }
}
Write-Host "Done converting javap outputs to java skeletons"