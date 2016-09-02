import React, { Component } from 'react';
import { connect } from 'react-redux';
import Immutable from 'immutable';
import ReactDOM from 'react-dom';

import ChatHistory from './ChatHistory';
import ChatMessageWithGrouping from '../message/ChatMessageWithGrouping';

import * as scrollActions from '../redux/actions/scroll-actions';

import { getCurrentRoom } from '../redux/selectors/rooms-selectors';
import { getScrollToMessageId } from '../redux/selectors/ui-selectors';
import { getAuthUserIsMod } from '../redux/selectors/auth-user-selectors';
import {
    getAllMessagesEntitiesForCurrentRoom,
    getFirstMessageIdForCurrentRoom
} from '../redux/selectors/message-entities-selectors';




class ChatThread extends Component {
  constructor(props) {
    super(props);
    this.handleOnScroll = this.handleOnScroll.bind(this);
  }

  componentDidUpdate() {
    const { scrollToMessageId, resetScrollToMessageId } = this.props;

    if (scrollToMessageId) {
      const thread = ReactDOM.findDOMNode(this.refs.thread);
      const scrollTo = ReactDOM.findDOMNode(this.refs[scrollToMessageId]);

      thread.scrollTop = scrollTo.offsetTop;
      resetScrollToMessageId();
    }
  }

  handleOnScroll(event) {
    const { handleMoreMessages } = this.props;
    const { srcElement } = event.nativeEvent;

    if (srcElement && srcElement.scrollTop === 0) {
      handleMoreMessages();
    }
  }

  renderMessages() {
    const { messages, users } = this.props;

    return messages.map((message, index) => {
      let previous = null;
      if (index > 0) {
        previous = messages.get(index - 1);
      }

      const user = users.get(message.get('username'));
      const uuid = message.get('uuid');
      return (
        <ChatMessageWithGrouping ref={uuid} key={uuid}
                                 currentMessage={message} previousMessage={previous} user={user}
                                 userIsMod={this.props.userIsMod}
        />
      );
    });
  }

  renderThread() {
    return (
      <ul id="thread" className="list-group list-group-lg no-radius m-b-none m-t-n-xxs">
        <ChatHistory />
        {this.renderMessages()}
      </ul>
    );
  }

  render() {
    return (
      <div className="row-row">
        <div ref="thread" id="thread_scrollparent" className="cell" onScroll={this.handleOnScroll}>
          <div className="cell-inner">
            {this.renderThread()}
            <div id="bottom-spacer" className="padder-v-sm bg-white b-l-3x b-l-white"></div>
          </div>
        </div>
      </div>
    );
  }
}

ChatThread.defaultProps = {
  messages: Immutable.List(),
  users: Immutable.List(),
  room: Immutable.Map(),
  scrollToMessageId: null,
  firstMessageID: null,
  resetScrollToMessageId: () => {},
  handleMoreMessages: () => {}
};

function mapStateToProps(state) {
  return {
    room: getCurrentRoom(state),
    users: state.get('users'),
    userIsMod: getAuthUserIsMod(state),
    scrollToMessageId: getScrollToMessageId(state),
    firstMessageID: getFirstMessageIdForCurrentRoom(state),
    messages: getAllMessagesEntitiesForCurrentRoom(state)
  };
}

function mapDispatchToProps(dispatch) {
  return {
    handleMoreMessages() {
      dispatch(scrollActions.handleMoreMessages());
    },
    resetScrollToMessageId() {
      dispatch(scrollActions.resetScrollToMessage());
    }
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(ChatThread);
