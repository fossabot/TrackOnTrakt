/*
 Copyright 2017 Josef Adamcik <josef.adamcik@gmail.com>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
*/
package cz.josefadamcik.trackontrakt.detail

import cz.josefadamcik.trackontrakt.data.api.model.MediaItem
import cz.josefadamcik.trackontrakt.data.api.model.MediaType
import paperparcel.PaperParcel
import paperparcel.PaperParcelable


@PaperParcel
data class MediaIdentifier(
    val type: MediaType,
    val id: Long
) : PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelMediaIdentifier.CREATOR

        public fun fromMediaItem(item: MediaItem): MediaIdentifier {
            val traktId = item.traktId
            if (traktId == null) {
                throw IllegalArgumentException("invalid item $item")
            } else {
                return MediaIdentifier(item.type, traktId)
            }
        }

        public fun fromMediaItemButShowForEpisode(item: MediaItem): MediaIdentifier {
            var traktId = item.traktId
            var type = item.type
            if (item.type == MediaType.episode) {
                traktId = item.show?.ids?.trakt
                type = MediaType.show
            }
            if (traktId == null) {
                throw IllegalArgumentException("invalid item $item")
            } else {
                return MediaIdentifier(type, traktId)
            }
        }


    }


}
