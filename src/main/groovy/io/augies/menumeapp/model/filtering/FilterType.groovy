package io.augies.menumeapp.model.filtering

enum FilterType {
    GREATER(">"),
    LESS("<"),
    EQUAL(":"),
    GREATER_OR_EQUAL(">:"),
    LESS_OR_EQUAL("<:"),
    NOT_EQUAL("!:"),
    CONTAINS("<>")

    String symbol

    FilterType(String symbol){
        this.symbol = symbol
    }

    static FilterType getBySymbol(String symbol){
        for(filter in values()){
            if(filter.symbol == symbol){
                return filter
            }
        }
        return null
    }
}