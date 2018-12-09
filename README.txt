To build a DI container...

+ construct an object using reflection
// assumption: class only has one constructor (reason: simplicity)

+ now make the object a singleton
// note: Joshua Block recommends making a singleton using an enum.
// Since I'm writing a DI container, I can't assume all objects handed to me will be enums.
// http://www.informit.com/articles/article.aspx?p=1216151&seqNum=3
++ how does Spring do this?
++ stores singletons in a ConcurrentHashMap<String, Object>
