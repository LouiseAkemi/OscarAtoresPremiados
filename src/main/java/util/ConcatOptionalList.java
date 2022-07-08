package util;
import model.AtorGanhadorOscar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConcatOptionalList <T> {

    public static <T> List<T> concat(Optional<List<T>> list1, Optional<List<T>> list2){

        return  Stream.of(list1, list2)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
