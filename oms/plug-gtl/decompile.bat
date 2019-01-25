For /r .\ %%G IN (.) do (
cd   %%G 
jad *
rename *.jad *.java

)
