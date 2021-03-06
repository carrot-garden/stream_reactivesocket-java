/*
 * Copyright 2016 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.reactivesocket.reactivestreams.extensions.internal;

import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;

public class EmptySubjectTest {

    @Test(timeout = 10000)
    public void testOnComplete() throws Exception {
        EmptySubject subject = new EmptySubject();
        TestSubscriber<Void> subscriber = TestSubscriber.create();
        subject.subscribe(subscriber);

        subscriber.assertNotTerminated();
        subject.onComplete();

        subscriber.assertComplete();
        subscriber.assertNoErrors();
    }

    @Test(timeout = 10000)
    public void testOnError() throws Exception {
        EmptySubject subject = new EmptySubject();
        TestSubscriber<Void> subscriber = TestSubscriber.create();
        subject.subscribe(subscriber);

        subscriber.assertNotTerminated();
        subject.onError(new NullPointerException());

        subscriber.assertNotComplete();
        subscriber.assertError(NullPointerException.class);
    }

    @Test(timeout = 10000)
    public void testOnErrorBeforeSubscribe() throws Exception {
        EmptySubject subject = new EmptySubject();
        subject.onError(new NullPointerException());
        TestSubscriber<Void> subscriber = TestSubscriber.create();
        subject.subscribe(subscriber);
        subscriber.assertNotComplete();
        subscriber.assertError(NullPointerException.class);
    }

    @Test(timeout = 10000)
    public void testCompleteBeforeSubscribe() throws Exception {
        EmptySubject subject = new EmptySubject();
        subject.onComplete();
        TestSubscriber<Void> subscriber = TestSubscriber.create();
        subject.subscribe(subscriber);
        subscriber.assertComplete();
        subscriber.assertNoErrors();
    }
}