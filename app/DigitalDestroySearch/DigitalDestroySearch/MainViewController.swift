//
//  ViewController.swift
//  DigitalDestroySearch
//
//  Created by Emilio Colindres on 8/7/16.
//  Copyright © 2016 Emilio Colindres. All rights reserved.
//

import UIKit


// MARK: - UIViewController

class MainViewController: UIViewController {
    
    // MARK: Properties
    
    @IBOutlet weak var mSearchBar: UISearchBar!
    @IBOutlet weak var mSearchButton: UIButton!
    
    var results = [Result]()

    let sQueryURL = "http://52.3.149.50:8080/search?q="
    
    // MARK: View Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        UIApplication.sharedApplication().statusBarStyle = .Default
        
        navigationController?.interactivePopGestureRecognizer?.delegate = self
        navigationController?.hidesBarsOnSwipe = false
        navigationController?.navigationBarHidden = true
        
        mSearchBar.delegate = self
        mSearchBar.placeholder = "Search Wikipedia..."
        mSearchBar.backgroundColor = UIColor.whiteColor()
        mSearchBar.barTintColor = UIColor.whiteColor()

        mSearchButton.addTarget(self, action: #selector(MainViewController.searchButtonTapped), forControlEvents: UIControlEvents.TouchUpInside)
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    // MARK: Navigation
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        let resultsVC = segue.destinationViewController as! ResultsViewController
        resultsVC.results = self.results
    }
    
    
    // MARK: Actions
    
    @IBAction func searchButtonTapped() {
        results.removeAll()
        
        let searchURL = NSURL(string: (sQueryURL + mSearchBar.text!.lowercaseString.stringByAddingPercentEncodingWithAllowedCharacters(.URLHostAllowedCharacterSet())!))
        print(searchURL)
        if let resultJSONData = NSData(contentsOfURL: searchURL!) {
            print(resultJSONData)
            do {
                if let jsonArray = try NSJSONSerialization.JSONObjectWithData(resultJSONData, options: []) as? [NSDictionary] {
                    for (_, jsonResult) in jsonArray.enumerate() {
                        let result = Result(json: jsonResult)
                        print(result)
                        results.insert(result, atIndex: 0)
                    }
                } else {
                    print("error with json result serialization")
                }
            } catch let jsonError as NSError {
                print(jsonError.localizedDescription)
            }
        } else {
            print("error when fetching data from search url")
        }
        
        performSegueWithIdentifier("segue", sender: nil)
    }
}

// MARK: - UIGestureRecognizerDelegate

extension MainViewController: UIGestureRecognizerDelegate {
    
    func gestureRecognizer(gestureRecognizer: UIGestureRecognizer, shouldRecognizeSimultaneouslyWithGestureRecognizer otherGestureRecognizer: UIGestureRecognizer) -> Bool {
        return true
    }
}

// MARK: - UISearchBarDelegate

extension MainViewController: UISearchBarDelegate {
    
    func searchBar(searchBar: UISearchBar, textDidChange searchText: String) {
        // getContentForSearchText(searchText)
    }
    
    func searchBarTextDidBeginEditing(searchBar: UISearchBar) {
        searchBar.setShowsCancelButton(true, animated: true)
        // tableView.reloadData()
    }
    
    func searchBarCancelButtonClicked(searchBar: UISearchBar) {
        searchBar.endEditing(false)
        // tableView.reloadData()
    }
    
    func searchBarSearchButtonClicked(searchBar: UISearchBar) {
        searchBar.endEditing(false)
        searchButtonTapped()
        // tableView.reloadData()
    }
    
    func searchBarTextDidEndEditing(searchBar: UISearchBar) {
        searchBar.setShowsCancelButton(false, animated: true)
    }
}
