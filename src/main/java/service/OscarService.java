package service;
import model.AtorGanhadorOscar;
import model.OscarSummary;
import java.util.*;
import java.util.stream.Collectors;

public class OscarService {
    private final List<AtorGanhadorOscar> dadosOscar;

    public OscarService(List<AtorGanhadorOscar> dadosOscar) {
        this.dadosOscar = dadosOscar;
    }

    private List<OscarSummary> oscarSummaryList(List<AtorGanhadorOscar> atoresPremiados){
        return atoresPremiados
                .stream()
                .map(x -> {
                    String nome = x.getNome();
                    int idade = x.getIdade();
                    return new OscarSummary(nome, idade);
                })
                .collect(Collectors.toList());
    }

    private Map<String,Long> premiosPorAtor(List<OscarSummary> summaryList){
        return summaryList
                .stream()
                .collect(Collectors.groupingBy(OscarSummary::getNome, Collectors.counting()));
    }

    public void atorMaisJovemPremiado(){
        Optional<AtorGanhadorOscar> atorMaisJovemPremiado = dadosOscar
                .stream()
                .min(Comparator.comparingInt(AtorGanhadorOscar::getIdade));

        atorMaisJovemPremiado.ifPresent(actor -> System.out.printf("O ator/ A atriz mais jovem a ser premiado/a é %s aos %d anos. %n",
                atorMaisJovemPremiado.get().getNome(),atorMaisJovemPremiado.get().getIdade()));
    }

    private List<Map.Entry<String, Long>> maisPremiado(List<AtorGanhadorOscar> oscarList){
        var qtdPremiosPorAtor = premiosPorAtor(oscarSummaryList(oscarList));
        var summaryStatistics = qtdPremiosPorAtor.entrySet().stream()
                .collect(Collectors.summarizingLong(Map.Entry::getValue));

        return qtdPremiosPorAtor
                .entrySet().stream()
                .filter(a -> a.getValue() == summaryStatistics.getMax())
                .collect(Collectors.toList());
    }

    public void atorMaisPremiado(){
        List<Map.Entry<String, Long>> atorMaisPremiado = maisPremiado(dadosOscar);
        atorMaisPremiado.forEach(ator -> System.out.printf("O ator/ A atriz mais premiado/a é %s com o total de %d prêmios. %n",
                ator.getKey(),ator.getValue()));
    }

    public void atorJovemMaisPremiado() {
        List<AtorGanhadorOscar> jovemAtores = dadosOscar
                .stream()
                .filter(l -> l.getIdade() >= 18 && l.getIdade() <= 24)
                .collect(Collectors.toList());

        List<Map.Entry<String, Long>> jovemAtorMaisPremiado = maisPremiado(jovemAtores);
        jovemAtorMaisPremiado.forEach(ator -> System.out.printf("Jovem ator/atriz mais premiado: %s com o total de %d prêmio %n",
                ator.getKey(),ator.getValue()));

    }

}
