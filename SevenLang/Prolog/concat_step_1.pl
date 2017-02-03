concatenate([], List, List).
concatenate([Head|[]], List, [Head|List]).
concatenate([Head1|[Head2|[]]], List, [Head1, Head2|List]).
concatenate([Head1|[Head2|[Head3|[]]]], List, [Head1, Head2, Head3|List]).


concatenate([], List, List).
concatenate([Head|Tail1], List, [Head, Tail2]) :- concatenate(Tail1, List, Tail2).
