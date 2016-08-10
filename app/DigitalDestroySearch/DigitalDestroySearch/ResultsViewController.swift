//
//  ResultsViewController.swift
//  DigitalDestroySearch
//
//  Created by Emilio Colindres on 8/9/16.
//  Copyright © 2016 Emilio Colindres. All rights reserved.
//

import Foundation
import UIKit

class ResultsViewController: UIViewController {
    @IBOutlet weak var mTableView: UITableView!
    var results = [Result]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mTableView.dataSource = self
        
        mTableView.reloadData()
    }
    
}

extension ResultsViewController: UITableViewDataSource {
    func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return results.count;
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        var cell = tableView.dequeueReusableCellWithIdentifier("cell", forIndexPath: indexPath)
        cell.textLabel?.text = results[indexPath.row].url
        cell.detailTextLabel?.text = String(results[indexPath.row].score!)
        return cell
    }
}