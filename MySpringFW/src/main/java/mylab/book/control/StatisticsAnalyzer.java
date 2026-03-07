package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

// 출판물 통계 분석 클래스
public class StatisticsAnalyzer {

    // 1). 타입별 평균 가격 계산 메서드
    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Double> totalPrice = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            totalPrice.put(type, totalPrice.getOrDefault(type, 0.0) + pub.getPrice());
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> averagePrice = new HashMap<>();
        for (String type : totalPrice.keySet()) {
            averagePrice.put(type, totalPrice.get(type) / count.get(type));
        }

        return averagePrice;
    }

    // 2). 출판물 유형 분포 계산 메서드
    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> count = new HashMap<>();

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> distribution = new HashMap<>();
        for (String type : count.keySet()) {
            distribution.put(type, (double) count.get(type) / publications.length * 100);
        }

        return distribution;
    }

    // 3). 특정 연도 출판물 비율 계산 메서드
    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int count = 0;
        for (Publication pub : publications) {
            if (pub.getPublishDate().substring(0, 4).equals(year)) {
                count++;
            }
        }
        return (double) count / publications.length * 100;
    }

    // 4). 출판물 타입 확인 헬퍼 메서드
    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) {
            return "소설";
        } else if (pub instanceof Magazine) {
            return "잡지";
        } else if (pub instanceof ReferenceBook) {
            return "참고서";
        } else {
            return "기타";
        }
    }

    // 5). 통계 정보 출력 메서드
    public void printStatistics(Publication[] publications) {
        DecimalFormat df = new DecimalFormat("#,###.##");

        System.out.println("===== 출판물 통계 분석 =====");

        // 1. 타입별 평균 가격
        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> avgPrice = calculateAveragePriceByType(publications);
        for (Map.Entry<String, Double> entry : avgPrice.entrySet()) {
            System.out.println("   - " + entry.getKey() + ": " + df.format(entry.getValue()) + "원");
        }

        // 2. 출판물 유형 분포
        System.out.println();
        System.out.println("2. 출판물 유형 분포:");
        Map<String, Double> distribution = calculatePublicationDistribution(publications);
        for (Map.Entry<String, Double> entry : distribution.entrySet()) {
            System.out.println("   - " + entry.getKey() + ": " + df.format(entry.getValue()) + "%");
        }

        // 3. 2007년도 출판 비율
        System.out.println();
        double ratio = calculatePublicationRatioByYear(publications, "2007");
        System.out.println("3. 2007년에 출판된 출판물 비율: " + df.format(ratio) + "%");
    }
}
