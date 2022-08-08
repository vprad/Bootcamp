package com.example.demo.controller;

import com.example.demo.exception.UnknownDeveloperException;
import com.example.demo.exception.UnknownFeedException;
import com.example.demo.model.Developer;
import com.example.demo.model.Feed;
import com.example.demo.model.Login;
import com.example.demo.repositories.FeedRepository;
import com.example.demo.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    FeedService feedService;

    @Autowired
    FeedRepository feedRepository;

    @GetMapping("/check")
    public String check() {
        return "Feed Service";
    }

    @GetMapping("/allUsers")
    public List<Feed> getAllFeed() {
        return feedService.fetchAll();
    }
    @RequestMapping("/getByFeedId/{feedId}")
    public ResponseEntity<Feed> findById(@PathVariable(value = "feedId") Integer feedId){
        Feed feed = feedRepository.getById(feedId);
        if(feed!=null) {
            return new ResponseEntity<>(feed, HttpStatus.OK);
        }
        else {
            return	new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/getFeed/dev/{devId}")
    public List<Feed> getFeedByDeveloper(@PathVariable(value = "devId") Integer devId) throws UnknownDeveloperException {

        return feedService.getFeedsByDeveloper(devId);
    }

    @GetMapping("/getFeedByTopic/{Topic}")
    public List<Feed> getFeedsByTopic(@PathVariable(value = "Topic") String Topic) {
        System.out.println(Topic);
        return feedService.getFeedsByTopic(Topic);
    }
    @PutMapping("/UpdateFeed")
    public ResponseEntity<Feed> update(@RequestBody Feed obj){
        feedService.editFeed(obj, obj.getFeedId());
        return new ResponseEntity<Feed>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/removeFeed/{feedId}")
    public String removeFeed(@PathVariable(value="feedId") Integer feedId) throws UnknownFeedException
    {
        feedService.removeFeed(feedId);
        return "Deleted";
    }

    @GetMapping("/getFeedByKeyword/{Keyword}")
    public List<Feed> getFeedsByKeyword(@PathVariable(value = "Keyword") String Keyword) {

        return feedService.getFeedsByKeyword(Keyword);
    }
    @PostMapping("/feedService/newFeed")
    public ResponseEntity <Feed> save(@RequestBody Feed obj){
        feedService.addFeed(obj);
        return new ResponseEntity<Feed>(obj, HttpStatus.OK);
    }
    @RequestMapping("/feedService/likeFeed/feedIdanddevId")
    public int likeFeed(@RequestParam int feedId, @RequestParam int devId) {
        return feedService.like(feedId,devId );
    }




}
