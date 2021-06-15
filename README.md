# ListPerfComparatorMVC
List Concurrent Performance Comparator on Spring MVC
A new release of List Performance Comparator with web interface.

The List Concurrent Performance Comparator measures performance of different types of lists -
<code>CopyOnWriteArrList, SynchronizedRandomAccessList, ArrayList, LinkedList</code> -
in the case when the Lists are accessed by two concurrent threads performing <code>get()</code> or <code>add()</code> actions.

<p>The Comparator has got a web interface implemented with Spring MVC that consists of :</p>
<ul style="list-style-type: square;">
    <li>"Main" page with :
        <ul style="list-style-type: circle;">
            <li>current performance test configuration</li>
            <li>[Edit test parameters] button that redirects to the Edit page to update the configuration</li>
            <li>[Test performance!] button that starts the performance test and redirects to the Results page</li>
        </ul>
    </li>
    <li>"Edit" page with :
        <ul style="list-style-type: circle;">
            <li>list of supported data types current performance test configuration</li>
            <li>fields to update the configuration (pre-filled with the current configuration)</li>
            <li>[Save new test configuration!] button that saves entered configuration and redirects to the Main page</li>
            <li>[Cancel] button that cancels the update and redirects to the Main page</li>
        </ul>
    </li>
    <li>"Results" page with :
        <ul style="list-style-type: circle;">
            <li>a table containing current configuration of the performance test</li>
            <li>[Edit test parameters] button that redirects to the Edit page to update the configuration</li>
            <li>a table containing the results of the performance test</li>
            <li>[Test again!] button that starts the performance test again and updates the Results page</li>
            <li>[Back] button that redirects to the Main page</li>
        </ul>
    </li>
</ul>

<h3>Concurrent performance test description</h3>
<h4>Mechanism of retrieving data</h4>
Two <code>Callable</code> threads perform a selected action (<code>access</code> a member or <code>add</code> a member) on first and second halves of a list respectively.
Submitting the threads to <code>newFixedThreadPool</code> and starting them simultaneously via <code>CountDownLatch</code>.
The threads will compete to perform the action on the assigned part of the list.
<br><br>
<h4>Further data processing</h4>
The results time is aggregated separately for each thread of each list under test. Then averages are calculated in <code>ms</code>
and shown on the "Results" page.
<hr>


<br>
<i>Example of the "Main" page :</i>
<br>
<hr>
<p>Current parameters for the test</p>

<p>Type of List 1: LinkedList [688,13,-352,161,-804,487,284,-566,-935,...</p>
<p>Type of List 2: CopyOnWriteArrayList [103,106,907,897,446,-141,99,...</p>
<p>Number of elements: 1000</p>
<p>Min value of elements: -1000</p>
<p>Max value of elements: 1000</p>
<p>Operation on Lists: READ</p>
<p>Number of test cycles: 100</p>
<br>
[Edit test parameters]
<br><br>
[Test performance!]

<hr>

<br><br>
<i>Example of the "Edit" page :</i>
<br>
<hr>
<p>Supported List types:</p>
<ul>
    <li>CopyOnWriteArrayList</li>
    <li>SynchronizedRandomAccessList</li>
    <li>ArrayList</li>
    <li>LinkedList</li>
</ul>

<p>Supported List Operations:</p>
<ul>
    <li>READ</li>
    <li>ADD</li>
</ul>
<pre>
    Type of List 1: [LinkedList               ]
    Type of List 2: [CopyOnWriteArrayList     ]
    Number of elements: [1000                     ]
    Min element value: [-1000                    ]
    Max element value: [1000                     ]
    Operation to perform: [READ                     ]
    Number of test cycles: [100                      ]
</pre>
    <br/>
    [Save new test configuration!]
<br/>
[Cancel]

<hr>

<br><br>
<i>Example of the "Results" page :</i>
<br>
<hr>
<table style="show" border="true">
    <caption>Test configuration</caption>
    <thead>
    <tr align="center">
        <th>Operation under test</th>
        <th>Number of elements</th>
        <th>Min value</th>
        <th>Max value</th>
        <th>Number of test cycles</th>
    </tr>
    </thead>
    <tbody>
    <tr align="center">
        <td><p>READ</p></td>
        <td><p>1000</p></td>
        <td><p>-1000</p></td>
        <td><p>1000</p></td>
        <td><p>100</p></td>
    </tr>
    </tbody>
</table>
<br>
[Edit test parameters]
<br><br>
<table border="true">
    <caption>Test results</caption>
    <thead>
    <tr align="center">
        <th>List type under test</th>
        <th>Thread 1 time, ms</th>
        <th>Thread 2 time, ms</th>
    </tr>
    </thead>
    <tbody>
    <tr align="center">
        <td><p>LinkedList</p></td>
        <td><p>324</p></td>
        <td><p>331</p></td>
    </tr>
    <tr align="center">
        <td><p>CopyOnWriteArrayList</p></td>
        <td><p>9</p></td>
        <td><p>10</p></td>
    </tr>
    </tbody>
</table>
<br>
[Test again!]
<br><br>
[Back]

<hr>

<br>
<p>
    @author S.V.
    @version 1.0.0
    @since 2021-06-15
</p>
