package com.nucleoti.searching.core.info.Service;

import com.nucleoti.searching.core.info.model.RequesSunatJuri;
import com.nucleoti.searching.core.info.model.ResponGenerally;

public interface ApiService  {
    ResponGenerally retrieveInfoPersonaSunat(String numDocum);
}
