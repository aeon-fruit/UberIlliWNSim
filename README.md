# Uber WN IlliSim
An extension of Illinois WNSim as a client library.

## Context
Illinois WNSim is an excellent Java library for measuring word similarity based on WordNet&reg; synsets (sets of cognitive synonyms).  
It implements several state-of-the-art similarity measures, but it has few unfortunate downsides. 
First of all, it exposes only two of the implemented measures (perhaps the rest of them are not quite polished). 
The least of all, it has a rather inconvenient way of handling file-based configuration. 
Yet the biggest of all, Illinois WNSim has a restrictive clause in its license regarding the distribution of derivative code. 
That pushed me to make this tiny extension as a client library with a dependency to the Illinois WNSim artifact published on Maven Central Repository.

As implied above, this client extension has two goals :
1. to expose all the similarity measures implemented in Illinois WNSim; and
2. to rid of the main API of the `ResourceManager` class and replace its optional use with the mandatory path argument in the construction of the word similarity comparator.

## User guide
This library requires WordNet&reg; 3.0 which is available on [the official source](https://wordnet.princeton.edu/download/current-version). 
Obviously, the downloaded archive should be extracted to the file system to have a path that could be fed to the main API.

The most common use of the library would be to instantiate `UberWordSim` and call its `compare` method like the following:
```java
class Example {
    
    public static void main(String[] args) {
        String first = "bird";
        String second = "animal";

        try {
            for (Measure measure : Measure.values()) {
                System.out.println("Measure: " + measure);
                UberWordSim uws = new UberWordSim(measure, "/path/to/word/net/dict/");
                
                System.out.println("Similarity between " + first + " and " + second + " = " + uws.compare(first, second));
                System.out.println("Similarity between " + second + " and " + first + " = " + uws.compare(second, first));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
```

## Disclaimer and citations
Before using Uber WN IlliSim, you should be fully aware, while this library is under the MIT license, its main dependency, namely Illinois WNSim, has a proprietary license.

https://cogcomp.seas.upenn.edu/page/software_view/IllinoisWNSimJava

Princeton University "About WordNet." [WordNet](https://wordnet.princeton.edu/). Princeton University. 2010.