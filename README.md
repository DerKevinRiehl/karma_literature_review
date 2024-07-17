# Resource Allocation with Karma – A Review
This is the online repository for the literature review paper on Karma.


## Contents of the repository
It contains following files that are the result of topic modelling with Latent Dirichlet Allocation on the literature corpus (list of all documents used):

- **LiteratureCorpus.xlsx** contains a list of all papers that were found and considered in this study.
- **LDA_Topic_Word_Lists.xlsx** contains a list of manually selected words from the top 100 words for each topic.
- **LDA_Topic_Word_matrix.xlsx** and **LDA_Topic_Document_matrix.xlsx** contain the probability matrixes that connect topics with words respectively topics with documents.
- **models/** is a folder containing the rendered topic models from multiple runs with different seeds (topic models can be loaded with [tomotopy](https://bab2min.github.io/tomotopy/v0.7.1/en/) from a ZIP file, which is too big to be uploaded here, but available upon request from the authors)
- **code/** is a folder contianing the source codes (Python & Java) to process the downloaded data, and conduct topic modelling with Latent Dirichlet Allocation. The Java code does not need specific software packages. The Python code needs two dependencies that are declared in python_requirements.txt


## Text Preprocessing Outline

### Step 1: PDF files to TXT files (A_PdfToTxt.java)

In this step downloaded PDF files are converted to TXT files. The resulting output for a single paper could look as follows:
```
17th International Symposium INFOTEH-JAHORINA, 21-23 March 2018
978-1-5386-4907-7/18/$31.00 ©2018 IEEE
Blockchain Technology, Bitcoin, and Ethereum: A
Brief Overview
Dejan Vuji?i?, Dijana Jagodi?, Siniša Ran?i?
Faculty of Technical Sciences in ?a?ak
University of Kragujevac
?a?ak, Serbia
dejan.vujicic@ftn.kg.ac.rs, dijana.jagodic@ftn.kg.ac.rs, sinisa.randjic@ftn.kg.ac.rs
Abstract—The blockchain technology is a relatively new approach
in the field of information technologies. As one of its first
implementations, bitcoin as a cryptocurrency has gained a lot of
attention. Together with Ethereum, blockchain implementation
with focus on smart contracts, they represent the very core of
modern cryptocurrency development. This paper is meant to give
a brief introduction to these topics.
Keywords – Bitcoin; blockchain; cryptocurrency; Ethereum;
smart contracts
I. INTRODUCTION
Bitcoin and blockchain technology have begun to shape and
define new aspects in the computer science and information
```

### Step 2: Preprocessing of plain text (B_PlainTextPreprocessor.java)

The raw text from PDFs (see example above) contains many special characters, line breaks, and unnecessary information.
Therefore, we need to preprocess the plain text in order to get more useful bits of the text.

In this step, line breaks, special characters (such as commas, dots, etc.), stop words (e.g. "and", "the"), duplicate lines (e.g. at the bottom a journal paper the title of the journal), and non-English characters (e.g. "ö", "ä") are removed. What's more, the text is decapitalized (all words transformed to lower case). Finally, the words are stemmed using Porter stemming, to remove variants of different words (e.g. "learned", "learning", "learners" -> "learn").

The resulting output for a single paper could look as follows:
```
th international symposium infoteh jahorina march ieee blockchain technology bitcoin ethereum brief overview dejan vuji dijana jagodi sini?a ran faculty technical sciences ak university kragujevac ak serbia dejan vujicic ftn kg ac rs dijana jagodic ftn kg ac rs sinisa randjic ftn kg ac rs abstract?the blockchain technology relatively new approach field information technologies one its first implementations bitcoin cryptocurrency gained lot attention 
```

### Step 3: N-Gram analysis and filtering (C_NGramAnalysis.py)

While the text looks more useful already, it might contain groups of words (n-grams) that refer to one term (e.g. "computer science", "blockchain technology"), and therefore should treated as a single word when processed by topic modelling. We therefore analyse n-grams of different lengths and replace groups by single words (e.g. "#computerscience", "#blockchaintechnology").

```
th intern symposium infoteh jahorina march #blockchaintechnology blockchain technolog bitcoin ethereum brief overview dejan vuji dijana jagodi sini ran faculti technic scienc ak univers kragujevac ak serbia dejan vujic ftn kg ac rs dijana jagod ftn kg ac rs sinisa randjic ftn kg ac rs abstract the #blockchaintechnology blockchain technolog rel new approach field inform technolog one first implement bitcoin cryptocurr gain lot attent togeth ethereum
```

### Step 4: Topic modelling with Latent Dirichlet Allocation (D_TopicModelling.py)

Now that the text is cleaned, and n-grams are replaced, we are ready to conduct topic modelling analysis.
Details on the models and results can be found in folder "models/".

## Citations
Please cite our paper if you find this work useful:

[APA-LIKE-CITATION TBD]

```
@article{riehl2024karma,
    title={Resource Allocation with Karma – A Review},
    author={Riehl, Kevin and Kouvelas, Anastasios and Makridis, Michalis}
    journal={Economies},
    year={2024}
}
```

