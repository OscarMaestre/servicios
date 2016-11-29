extraer_listados.py
call make html
copiar.py
call make latex
cd _build\latex
call pdflatex Servicios_y_procesos.tex
call pdflatex Servicios_y_procesos.tex
cd ..
cd ..
call git commit -a --allow-empty-message -m ''
call git push
