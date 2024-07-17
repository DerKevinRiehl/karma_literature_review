# Part of: "Karma Literature Review", Copyrights Kevin Riehl 2024, <kriehl@ethz.ch>

# Imports
from os import listdir
from os.path import isfile, join
import datetime
import tomotopy as tp

# Methods
def excludeWords(text):
    text = text.replace(" use ", " ")
    text = text.replace(" user ", " ")
    return text

# Constants
folder = "./4_TXTs_CleanedNgramStem"

# Load Documents
documents = [f for f in listdir(folder) if isfile(join(folder, f))]
document_excluded = ["380.txt","160.txt","544.txt","335.txt","138.txt","166.txt", # chinese
                     "532.txt","545.txt", # greek
                     "217.txt","336.txt","301.txt","33.txt","390.txt", # russian
                     "287.txt", # arab
                     "461.txt", # korean 
                     "288.txt","364.txt","323.txt","363.txt", # german
                     "530.txt","543.txt","419.txt","542.txt", # spanish
                     "116.txt", #türkish
                     ]
documentsFiltered = []
for d in documents:
    if(d not in document_excluded):
        documentsFiltered.append(d)
documents = documentsFiltered

# Load Corpus
corpus = []
print("loading documents...")
for doc in documents:
    file = open(folder+"/"+doc, "r", encoding="utf-8"); 
    article_text = file.read()
    file.close()
    corpus.append(excludeWords(article_text).strip().split())
print("\nloading finished...")

# Train some Models
numTopics=20
mdl = tp.LDAModel(min_cf=20, min_df=0, rm_top=0, k=numTopics, alpha=50/numTopics, eta=0.2)
for corp in corpus:
    mdl.add_doc(corp)
for i in range(0, 100, 10):
    mdl.train(10)
print(numTopics, mdl.perplexity, mdl.ll_per_word)

# Print Top 10 Words of each topic
for k in range(mdl.k):
    print('Top 10 words of topic #{}'.format(k))
    print(mdl.get_topic_words(k, top_n=10))

# Print Document - Topic Inference
lst = []
for doc in documents:
    print(doc)
    article_text=""
    file = open(folder+"/"+doc, "r", encoding="utf-8")
    article_text = file.read()
    file.close()
    doc_inst = mdl.make_doc(excludeWords(article_text).strip().split())
    mdl.infer(doc_inst)
    lst.append([doc, mdl.infer(doc_inst)[0][4]])
    print(doc, mdl.infer(doc_inst)[0][4])
import pandas as pd
df = pd.DataFrame(lst, columns=["doc", "topic4"])


