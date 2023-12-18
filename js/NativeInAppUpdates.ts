import { TurboModule, TurboModuleRegistry } from "react-native";

export interface Spec extends TurboModule {
  checkForUpdate(options: {
    stalenessDays: number;
  }): Promise<"update_exists" | "update_downloaded" | "no_updates_available">;
  startFlexibleUpdate(): Promise<boolean>;
  installFlexibleUpdate(): Promise<boolean>;
}

export default TurboModuleRegistry.get<Spec>("RTNInAppUpdates") as Spec | null;
