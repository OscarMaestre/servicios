#!/bin/bash
./extraer_listados.py
./indentar.py
make html
make latex
cd _build/latex ; pdflatex Servicios_y_procesos.tex ; pdflatex Servicios_y_procesos.tex
cd ..
cd ..
./copiar.py
git add docs
git commit -a --allow-empty-message -m ''
git push

