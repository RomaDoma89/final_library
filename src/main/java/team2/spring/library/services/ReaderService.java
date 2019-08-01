package team2.spring.library.services;

import team2.spring.library.dto.ReaderStatisticDto;
import team2.spring.library.entities.Reader;

import java.util.List;

public interface ReaderService {
  List<Reader> findByName(String name);

  List<Reader> getBlackList();

  ReaderStatisticDto getUserStatistic(ReaderStatisticDto readerStatisticDto);
}
