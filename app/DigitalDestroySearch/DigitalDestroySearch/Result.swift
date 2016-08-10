//
//  Result.swift
//  DigitalDestroySearch
//
//  Created by Emilio Colindres on 8/9/16.
//  Copyright © 2016 Emilio Colindres. All rights reserved.
//

import Foundation

class Result {
    
    // MARK: Properties
    
    var id: Int?
    var title: String?
    var url: String?
    var score: Int?
    var summary: String?
    var timestamp: NSDate?
    
    // MARK: Initialization
    
    init(json: NSDictionary) {
        self.id = json["id"] as? Int
        self.title = json["title"] as? String
        self.url = json["url"] as? String
        self.score = json["score"] as? Int
        self.summary = json["summary"] as? String
        self.timestamp = json["timestamp"] as? NSDate
    }
}