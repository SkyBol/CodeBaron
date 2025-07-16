import { useEffect, useState } from "react"
import ChaosEntry from "../../../models/ChaosEntry";
import ChaosEntryService from "../../../services/ChaosEntryService";
import Pagination, { defaultPagination } from "../../../../../common-components/paging/Pagination";
import ChaosEntryList from "../../molecules/ChaosEntryList/ChaosEntryList";
import Paging from "../../../../../common-components/paging/Paging";


const ChaosTable = () => {
  const [chaosEntries, setChaosEntries] = useState<ChaosEntry[]>([]);
  const [paginationConfig, setPaginationConfig] = useState<Pagination>(defaultPagination);
  const [count, setCount] = useState<number>(0);

  useEffect(() => {
    const fetch = async () => {
      const entries = await ChaosEntryService.getAllPaged(paginationConfig.size, paginationConfig.page);
      setChaosEntries(entries.data);
    }

    const count = async () => {
      const count = await ChaosEntryService.count();
      setCount(count.data);
    }

    fetch();
    count();
  }, [paginationConfig]);

  return (
    <div>
      <ChaosEntryList chaosEntries={chaosEntries} />
      <Paging
        maxItems={count}
        pagination={paginationConfig}
        setPagination={setPaginationConfig}
      />
    </div>
  );
}

export default ChaosTable;