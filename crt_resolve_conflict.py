#!/usr/bin/env python

import re
import sys
import os

matcher = re.compile("^(>>>>)|(====)|(<<<<)")

if len(sys.argv) < 2:
    sys.stderr.write("Specify input file on commandline.\n")
else:
    name = sys.argv[1]
    backup_name = name + ".crt_resolve_backup"
    os.rename(name, backup_name)
    in_f = open( backup_name, "r" )
    out_f = open( name, "wb" )
    for l in in_f:
        m = matcher.match(l)
        if m == None:
            out_f.write(l)
    out_f.close()

