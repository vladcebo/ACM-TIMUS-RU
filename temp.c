		/* DOINA© - Device On the InterNet for Acquisition and Control */ 
		/* Thing  - An electronic device that implements STP and offers an interface for communicating */
		/* STP    - Simple Things Protocol */
		
		
		
		/* Operation for users:
		 * 	1) user registers to the web site
		 *  2) receive authentication token 
		 *  3) program the esp8266 device with wifi password, authentication token etc.
		 *  4) just use it
		 *  
		 * for developers:
		 * 	1) Add STP to your device
		 *  2) connect your device to DOINAC
		 *  3) Provide a configuration file for the device
		 *  4) provide application?
		 */
		
		
		/*  (Server -> Thing)
		 * 	Command format:	     |----------- cmd_len ------------|
		 * 
		 * 		<cmd_id><cmd_len><var_id><nr_args>[<arg_id><arg_val>]<check_sum>
		 * 		
		 * 		<cmd_id>   - 1 byte - identifies if it's a read/set command
		 *		<cmd_len>  - 1 byte
		 * 		<var_id>   - as specified in host configuration file
		 * 		<nr_args>  - number of arguments for the variable (skip if read)
		 * 		<arg_id>   - arg id (either if it's read or if it's write)
		 * 		<arg_val>  - arg value
		 * 
		 * (Thing -> Server)
		 * Response format:
		 * 		<resp_id><resp_len><err>[<err_id><err_text>][var_id][var_value]<check_sum>
		 * 
		 * 		<resp_id> -> read variable, event for variable, acknowledge for write, error.
		 *
		 * 		server sends commands to esp8266
		 * 		esp8266 sends commands to thing
		 *      thing interprets commands, either (also combining them in blocks)
		 *      thing responds back with result
		 * 	
		 * 
		 * 
		 * -> Should the events be attached to device or web server?
		 * 	  answer: attach custom events to web server
		 * 	  device events attach to actions, action types -> work and rule
		 * 
		 * 	  example state:  -> if (t > 10) => power off / power oN
		 * Thing Configuration (XML)
		 * 	<device>
		 * 		<name>DEVICE_NAME</name>
		 * 		
		 *		<states>
		 *			<state>
		 *				<id></id>
		 *				<name></name>
		 *				<type></type>
		 *				<configurable></configurable>
		 * 				<units></units>
		 *				<span>
		 *					<value>
		 *						<id></id>
		 *						<name></name>
		 *					</value>
		 *					...
		 *				</span>
		 * 			</state>
		 * 			...
		 * 		</states>
		 * 		
		 * 		<actions>
		 * 			<action>
		 * 				<name></name>
		 * 				<args>
		 * 					<arg_id></arg_id>
		 * 					<arg_name type=""></arg_name>
		 * 				</args>
		 * 			</action>
		 * 			...
		 * 
		 * 		</actions>
		 * 	
		 * 
		 * 
		 * 
		 */
		
		
		
		/* HOST Device can send events, alerts, acknowledgments etc. */
		
		/* Host Device:
		 * 	1) It can read data about temperature, humidity, status etc. (sense)
		 *  2) It can respond to commands (action)
		 *  3) It can generate events (alerts fall in this category)
		 * 
		 * 
		 * No command should be longer than 1kb
		 * if the command is longer than 1kb. -> todo: should be divided in chunks etc.
		 * STP specifications:
		 * 	

		 * 
		 * 
		 * On server side we have the XML configuration for the device
		 * where all the commands are defined, all the variables, their ids, and their names
		 * All have variables!!!
		 * 
		 * <device>
		 * 	<name>Smart Fridge</name>		<-- editable
		 *  
		 *  <data>
		 *  	<state id="0" settable="true" type="integer" span="finite">	<-- finite should contain values pairs
		 *  		<name>Status<name>
		 *  		<values>
		 *  			<value val="0">OFF</value>
		 *  			<value val="1">ON</value>
		 * 			</values>
		 * 			<events>
		 * 				<change>Status changed</change>
		 * 			</....>
		 * 		</state>
		 * 		<state id="1" settable="false" type="real" span="infinite" units="celsius">
		 * 			<name>Temperature</name>
		 * 			<timeout>100</timeout>  // how fast we can read or set the state
		 * 		</state>
		 * 	    <state id="2" settable="false" type="text">
		 * 			<name>Current TV Channel</name>
		 * 		</state>
		 *	<date>
		 *
		 *	<actions>
		 *		<action id="0">
		 *			<name>Next TV channel</name>
		 *			<args>
		 *				<name>skip<name>
		 *				<value type="integer"></value>
		 *		</action>
		 *  </actions>
		 * 	
		 *  
		 *  
		 * 
		 * 
		 * </device>
		 * 
		 * 
		 * 
		 * What kind of states do we have?
		 * finite sets {1, 5, 9}, {ON, OFF} etc. both can be setable or unsetable
		 * infinite set {0..3.4}		
		 * 
		 * 
		 * events/alerts to register (events are registered to variables).
		 */

		
		/* HOST 1 - Fridge
		 * Sense:
		 * 	  1) Power consumption		<- read value
		 *    2) up temperature			<- read value
		 *    3) down temperature		<- read value
		 *    4) current consumption	<- read value
		 *    5) is On/is Off			<- read state		
		 *    6) light bulb  isOn/isOff <- read state
		 *    
		 *    7) states: temperature up set (-21 -17 -15 -10)  -> set state
		 *               temperature down set (-15 -10 -5 0)   -> set state
		 *               items: count, products (for example added from the database) <- read data?
		 *               
		 * Action:
		 * 	  1) read x (a state or a variable, add continously?)
		 *    2) set state (power on, off, sens readings etc.)
		 */
		
		/* HOST 2 - Sensor kit 
		 *   1) power consumption
		 *   2) temperature 1
		 *   3) temperature 2
		 *   4) temperature 3
		 *   5) humidity 1
		 *   6) humidity 2
		 *   7) gas concentration 1 CO2
		 *   8) gas concentration 2 CH4
		 *   9) is on/is off
		 * 
		 */
		
		/* HOST 3 - Smart TV
		 *    1) power consumption
		 *    2) is on/off
		 *    3) what TV channel?
		 *    4) what is quality?
		 *    5) what is serial ID of TV
		 *    6) next channel
		 *    7) prev channel
		 *    8) turn on/off
		 *    9) select channel -> return success if channel is set, not success if otherwise
		 */
		
		/* HOST requirements:
		 * 
		 * -> HOST should implement STP (Simple Things Protocol)
		 *     to be able to connect to esp8266
		 *
		 * 
		 * 
		 */