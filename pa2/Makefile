#------------------------------------------------------------------------------
# A Makefile with macros
#------------------------------------------------------------------------------
#Cole Teza
#1361038
#cs12b pa2

JAVASRC = Search.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Search
CLASSES = Search.class
JARFILE = pa2
SUBMIT = submit cmps012b-pt.s16 pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
