package com.example.demo.service;


import com.example.demo.exception.UnknownDeveloperException;
import com.example.demo.exception.UnknownFeedException;
import com.example.demo.model.Feed;
import com.example.demo.repositories.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Repository
public class FeedServiceImpl implements FeedService {


    @Autowired
    FeedRepository feedRepository;


    @Override
    public Feed addFeed(Feed feed) {
        // TODO Auto-generated method stub
        return feedRepository.save(feed);
    }

    @Override
    public Feed editFeed(Feed feed,int feedId) {
        // TODO Auto-generated method stub
        Feed rest = feedRepository.getById(feed.getFeedId());
        rest.setFeedId(feed.getFeedId());
        rest.setDevId(feed.getDevId());
        rest.setFeedDate(feed.getFeedDate());
        rest.setFeedTime(feed.getFeedTime());
        rest.setQuery(feed.getQuery());
        rest.setTopic(feed.getTopic());
        rest.setRelevance(feed.getRelevance());
        //rest.setResponses(rest.getResponses());
        rest.setTotalComments(feed.getTotalComments());

        return feedRepository.save(rest);
    }





    @Override
    public void removeFeed(int feedId) throws UnknownFeedException {
        // TODO Auto-generated method stub
       feedRepository.deleteById(feedId);
    }

    @Override
    public List<Feed> getFeedsByDeveloper(int devId) throws UnknownDeveloperException {
        // TODO Auto-generated method stub
        List<Feed> feeds = feedRepository.findAll();
        List<Feed> output = new ArrayList<>();
        for(Feed feed : feeds) {
            if(feed.getDevId() == devId) {
                output.add(feed);

            }
            else{
                System.out.println("Developer Id is not found");
            }

        }
        return output;
    }

    @Override
    public List<Feed> getFeedsByKeyword(String keyword) {
        // TODO Auto-generated method stub
        List<Feed> feeds = feedRepository.findAll();
        List<Feed> output = new ArrayList<>();
        for(Feed feed: feeds) {
            if(feed.getQuery().contains(keyword)){
                output.add(feed);
                System.out.println("Display Output"+output);
            }
            else{
                System.out.println("Keyword is not found");
            }
        }
        return (output);
    }

    @Override
    public List<Feed> getFeedsByTopic(String Topic) {
        // TODO Auto-generated method stub
        List<Feed> feeds = feedRepository.findAll();
        System.out.println("Inside service layers"+feeds);
        List<Feed> output = new ArrayList<>();
        for(Feed feed : feeds) {
            if(feed.getTopic().equals(Topic)) {

                output.add(feed);
                System.out.println("Display Output"+output);
            }
            else{
                System.out.println("Topic is not found");
            }
        }
        return (output);
    }

    @Override
    public List<Feed> fetchAll() {
        return feedRepository.findAll();
    }

    @Override
    public int like(int feedId, int devId) {
        Feed feed=feedRepository.getById(feedId);
           int count=feed.getRelevance();
            if(feed.getRelevance()>=0) {
               count++;
            }

        feed.setRelevance(count);
        feedRepository.save(feed);
        return count;
    }
}
