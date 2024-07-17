# Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>

# Imports
from os import listdir
from os.path import isfile, join
import datetime

# Methods
def convertNGram2String(ngram):
    txt = ngram[0]
    for g in range(1,len(ngram)):
        txt = txt + " " + ngram[g]
    return " "+txt+" "
def removeNGramFromString(nGrams, txt):
    for gram in nGrams:
        txt = txt.replace(convertNGram2String(gram)," ")
    while("  " in txt):
        txt = txt.replace("  ", " ")
    return txt    
def substituteNGramFromString(nGrams, substitutes, txt):
    for g in range(0, len(nGrams)):
        ngramstring = convertNGram2String(nGrams[g])
        txt = txt.replace(ngramstring," "+substitutes[g]+" "+ngramstring)
    while("  " in txt):
        txt = txt.replace("  ", " ")
    return txt 
def loadNGrams(txt_file):   
    f=open(txt_file, "r")
    contents = f.read()
    lines = contents.split("\n")
    lines = lines[:-1]   
    ngrams = list()
    for i in range(0,len(lines)):
        ngram = lines[i]
        ngram = ngram.split(")")[0]
        ngram = ngram.split("(")[2]
        ngram = ngram.replace(" ","")
        ngram = ngram.replace("'","")
        ngram = ngram.split(",")
        ngram_List = [x for x in ngram if len(x)!=0]                
        ngrams.append(ngram_List)
    return ngrams
def loadNGrams4Subst(txt_file):   
    f=open(txt_file, "r")
    contents = f.read()
    lines = contents.split("\n")
    lines = lines[:-1]   
    ngrams = list()
    substs = list()
    for i in range(0,len(lines)):
        ngram = lines[i]
        subst = ngram.split(")")[1]
        subst = subst.split("'")[1]
        ngram = ngram.split(")")[0]
        ngram = ngram.split("(")[2]
        ngram = ngram.replace(" ","")
        ngram = ngram.replace("'","")
        ngram = ngram.split(",")
        ngrams.append(ngram)
        substs.append(subst)
    return ngrams, substs
def cleanChars(corpus_text):
    corpus_text = corpus_text.replace('\x01', " ")
    corpus_text = corpus_text.replace('\x04', " ")
    corpus_text = corpus_text.replace('\x05', " ")
    corpus_text = corpus_text.replace('\x07', " ")
    corpus_text = corpus_text.replace('\x08', " ")
    corpus_text = corpus_text.replace('\x0b', " ")
    corpus_text = corpus_text.replace('\x0c', " ")
    corpus_text = corpus_text.replace('\x0e', " ")
    corpus_text = corpus_text.replace('\x10', " ")
    corpus_text = corpus_text.replace('\x11', " ")
    corpus_text = corpus_text.replace('\x12', " ")
    corpus_text = corpus_text.replace('\x13', " ")
    corpus_text = corpus_text.replace('\x16', " ")
    corpus_text = corpus_text.replace('\x17', " ")
    corpus_text = corpus_text.replace('\x18', " ")
    corpus_text = corpus_text.replace('\x19', " ")
    corpus_text = corpus_text.replace('\x1a', " ")
    corpus_text = corpus_text.replace('\x1b', " ")
    corpus_text = corpus_text.replace('\x1c', " ")
    corpus_text = corpus_text.replace('\x1d', " ")
    corpus_text = corpus_text.replace('\x1e', " ")
    corpus_text = corpus_text.replace('\x1f', " ")
    corpus_text = corpus_text.replace('\x7f', " ")
    corpus_text = corpus_text.replace('\xa0', " ")
    corpus_text = corpus_text.replace('\xad', " ")
    corpus_text = corpus_text.replace('"', " ")
    corpus_text = corpus_text.replace('~', " ")
    corpus_text = corpus_text.replace('¡', " ")
    corpus_text = corpus_text.replace('¢', " ")
    corpus_text = corpus_text.replace('£', " ")
    corpus_text = corpus_text.replace('¤', " ")
    corpus_text = corpus_text.replace('¥', " ")
    corpus_text = corpus_text.replace('¨', " ")
    corpus_text = corpus_text.replace('ª', " ")
    corpus_text = corpus_text.replace('¬', " ")
    corpus_text = corpus_text.replace('®', " ")
    corpus_text = corpus_text.replace('¯', " ")
    corpus_text = corpus_text.replace('±', " ")
    corpus_text = corpus_text.replace('¶', " ")
    corpus_text = corpus_text.replace('·', " ")
    corpus_text = corpus_text.replace('¸', " ")
    corpus_text = corpus_text.replace('¼', " ")
    corpus_text = corpus_text.replace('½', " ")
    corpus_text = corpus_text.replace('¾', " ")
    corpus_text = corpus_text.replace('¿', " ")
    corpus_text = corpus_text.replace('×', " ")
    corpus_text = corpus_text.replace('ß', " ")
    corpus_text = corpus_text.replace('à', " ")
    corpus_text = corpus_text.replace('á', " ")
    corpus_text = corpus_text.replace('â', " ")
    corpus_text = corpus_text.replace('ã', " ")
    corpus_text = corpus_text.replace('ä', " ")
    corpus_text = corpus_text.replace('å', " ")
    corpus_text = corpus_text.replace('æ', " ")
    corpus_text = corpus_text.replace('ç', " ")
    corpus_text = corpus_text.replace('è', " ")
    corpus_text = corpus_text.replace('é', " ")
    corpus_text = corpus_text.replace('ë', " ")
    corpus_text = corpus_text.replace('ì', " ")
    corpus_text = corpus_text.replace('í', " ")
    corpus_text = corpus_text.replace('î', " ")
    corpus_text = corpus_text.replace('ï', " ")
    corpus_text = corpus_text.replace('ð', " ")
    corpus_text = corpus_text.replace('ñ', " ")
    corpus_text = corpus_text.replace('ó', " ")
    corpus_text = corpus_text.replace('ô', " ")
    corpus_text = corpus_text.replace('ö', " ")
    corpus_text = corpus_text.replace('÷', " ")
    corpus_text = corpus_text.replace('ø', " ")
    corpus_text = corpus_text.replace('ú', " ")
    corpus_text = corpus_text.replace('ü', " ")
    corpus_text = corpus_text.replace('þ', " ")
    corpus_text = corpus_text.replace('š', " ")
    corpus_text = corpus_text.replace('ž', " ")
    corpus_text = corpus_text.replace('ˆ', " ")
    corpus_text = corpus_text.replace('˜', " ")
    corpus_text = corpus_text.replace('‚', " ")
    corpus_text = corpus_text.replace('„', " ")
    corpus_text = corpus_text.replace('†', " ")
    corpus_text = corpus_text.replace('‡', " ")
    corpus_text = corpus_text.replace('•', " ")
    corpus_text = corpus_text.replace('…', " ")
    corpus_text = corpus_text.replace('‰', " ")
    corpus_text = corpus_text.replace('€', " ")
    corpus_text = corpus_text.replace('™', " ")
    corpus_text = corpus_text.replace("?", " ")
    corpus_text = corpus_text.replace('\x01', " ")
    corpus_text = corpus_text.replace('\x03', " ")
    corpus_text = corpus_text.replace('\x15', " ")
    corpus_text = corpus_text.replace('\x14', " ")
    corpus_text = corpus_text.replace('\x02', " ")
    corpus_text = corpus_text.replace('\x0f', " ")
    corpus_text = corpus_text.replace('\x06', " ")
    corpus_text = corpus_text.replace('«', " ")
    corpus_text = corpus_text.replace('»', " ")
    corpus_text = corpus_text.replace(' lw ', " ")
    corpus_text = corpus_text.replace(' le ', " file ")
    corpus_text = corpus_text.replace(' rst ', " first ")
    corpus_text = corpus_text.replace(' e ', " ")
    corpus_text = corpus_text.replace(' k ', " ")
    corpus_text = corpus_text.replace(' s ', " ")
    corpus_text = corpus_text.replace(' i ', " ")
    corpus_text = corpus_text.replace(' x ', " ")
    corpus_text = corpus_text.replace(' a ', " ")
    corpus_text = corpus_text.replace(' o ', " ")
    corpus_text = corpus_text.replace('\t', " ")
    while "  " in corpus_text:
        corpus_text = corpus_text.replace("  ", " ")
    corpus_text = corpus_text.replace(' pp ', " #peertopeer ")
    corpus_text = corpus_text.replace(' p p ', " #peertopeer ")
    corpus_text = corpus_text.replace(' peerto peer ', " #peertopeer ")
    corpus_text = corpus_text.replace(' peer topeer ', " #peertopeer ")
    corpus_text = corpus_text.replace(' peer peer ', " #peertopeer ")
    corpus_text = corpus_text.replace(' peertopeer ', " #peertopeer ")
    corpus_text = corpus_text.replace('di erent', "different")
    corpus_text = corpus_text.replace('sys ems', "systems")
    corpus_text = corpus_text.replace('sy em', "systems")
    corpus_text = corpus_text.replace('sy ems', "systems")
    corpus_text = corpus_text.replace('de nition', "define")
    corpus_text = corpus_text.replace('sel sh', "selfish")
    corpus_text = corpus_text.replace('di erence', "difference")
    corpus_text = corpus_text.replace('di cult', "difficult")
    corpus_text = corpus_text.replace('signi cant', "significant")
    corpus_text = corpus_text.replace('pro le', "profile")
    corpus_text = corpus_text.replace('speci ed', "specified")
    corpus_text = corpus_text.replace('ef ciency', "efficiency")
    corpus_text = corpus_text.replace('veri cation', "verification")
    corpus_text = corpus_text.replace('signi cantly', "significantly")
    corpus_text = corpus_text.replace('speci cation', "specification")
    corpus_text = corpus_text.replace('identi er', "identifier")
    corpus_text = corpus_text.replace('su cient', "sufficient")
    corpus_text = corpus_text.replace('sys ems', "systems")
    corpus_text = corpus_text.replace('con guration', "configuration")
    corpus_text = corpus_text.replace('identi ers', "identifiers")
    corpus_text = corpus_text.replace('bu er', "buffer")
    corpus_text = corpus_text.replace('de nes', "define")
    corpus_text = corpus_text.replace('de ned', "define")
    corpus_text = corpus_text.replace('de ne', "define")
    corpus_text = corpus_text.replace('work ow', "workflow")
    corpus_text = corpus_text.replace('bene ts', "benefits")
    corpus_text = corpus_text.replace('ef cient', "efficient")
    corpus_text = corpus_text.replace('speci cally', "specifically")
    corpus_text = corpus_text.replace('classi cation', "classification")
    corpus_text = corpus_text.replace('modi ed', "modified")
    corpus_text = corpus_text.replace('satis es', "satisfies")
    corpus_text = corpus_text.replace('satis ed', "satisfies")
    return corpus_text

# Parameters
corpus_folder = "./3_TXTs"
filter_folder = "./n_grams"
output_folder = "./4_TXTs_CleanedNgramStem"

# Generate List of Documents
documents = [f for f in listdir(corpus_folder) if isfile(join(corpus_folder, f))]
print(datetime.datetime.now())

# Load Filters BEFORE
filterFiles = [f for f in listdir(filter_folder) if isfile(join(filter_folder, f)) and f.endswith(".txt")]
filtersBefore = []
for file in filterFiles:
    print("apply filter "+file)
    filterType = file.split(".")[0].split("_")[-1]
    if(filterType=="DEL"):
        filtersBefore.append([filterType, loadNGrams(filter_folder+"/"+file)])
    if(filterType=="SUB"):
        ngram, substs = loadNGrams4Subst(filter_folder+"/"+file)
        filtersBefore.append([filterType, ngram, substs])
filterFiles = [f for f in listdir(filter_folder) if isfile(join(filter_folder, f)) and f.endswith(".txtstem")]
filtersAfter = []
for file in filterFiles:
    print("apply filter "+file)
    filterType = file.split(".")[0].split("_")[-1]
    if(filterType=="DEL"):
        filtersAfter.append([filterType, loadNGrams(filter_folder+"/"+file)])
    if(filterType=="SUB"):
        ngram, substs = loadNGrams4Subst(filter_folder+"/"+file)
        filtersAfter.append([filterType, ngram, substs])

# Document for Document Do Cleansing
print("loading documents...")
counter = 1
for f in documents:
    # Load
    file = open(corpus_folder+"/"+f, "r")
    article_text = file.read()
    file.close()
    # Do Cleansing
    article_text = cleanChars(article_text)
    # Apply Filters to Clean Text
    for filt in filtersBefore:
        filterType = filt[0]
        if(filterType=="DEL"):
            article_text = removeNGramFromString(filt[1], article_text)
        if(filterType=="SUB"):
            article_text = substituteNGramFromString(filt[1], filt[2], article_text)
    while "  " in article_text:
        article_text = article_text.replace("  ", " ")
    # STEMMING AND TOKENIZING
    from nltk.stem import PorterStemmer
    tokenized = article_text.split()
    ps = PorterStemmer()
    tokenized = [ps.stem(w) if ("#" not in w) else w for w in tokenized]
    # APPLY FILTER FOR STEMMING
    article_text = " ".join(tokenized)
    for filt in filtersAfter:
        filterType = filt[0]
        if(filterType=="DEL"):
            article_text = removeNGramFromString(filt[1], article_text)
        if(filterType=="SUB"):
            article_text = substituteNGramFromString(filt[1], filt[2], article_text)
    while "  " in article_text:
        article_text = article_text.replace("  ", " ")
    # Save
    file = open(output_folder+"/"+f, "w+", encoding="utf-8")
    file.write(article_text)
    file.close()
    print(f)