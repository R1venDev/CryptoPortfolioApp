package com.example.cryptoapp.services;

import com.example.cryptoapp.models.Portfolio;

public interface IPortfolioService extends IEntityService<Portfolio> {
    public double countPNL (Long portfolioId, double assetPriceNow);
}
