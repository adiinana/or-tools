// Copyright 2010-2021 Google LLC
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.ortools.sat;

import com.google.ortools.sat.CumulativeConstraintProto;

/**
 * Specialized cumulative constraint.
 *
 * <p>This constraint allows adding (interval, demand) pairs to the cumulative constraint
 * incrementally.
 */
public class CumulativeConstraint extends Constraint {
  public CumulativeConstraint(CpModel model) {
    super(model.getBuilder());
    this.model = model;
  }

  /// Adds a pair (interval, demand) to the constraint.
  public void addDemand(IntervalVar interval, LinearArgument demand) {
    CumulativeConstraintProto.Builder cumul = getBuilder().getCumulativeBuilder();
    cumul.addIntervals(interval.getIndex());
    cumul.addDemands(model.getLinearExpressionProtoBuilderFromLinearArgument(demand, false));
  }

  /// Adds a pair (interval, demand) to the constraint.
  public void addDemand(IntervalVar interval, long demand) {
    CumulativeConstraintProto.Builder cumul = getBuilder().getCumulativeBuilder();
    cumul.addIntervals(interval.getIndex());
    cumul.addDemands(model.getLinearExpressionProtoBuilderFromLong(demand));
  }

  private final CpModel model;
}
