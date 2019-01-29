head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ServerTryOrderProviderCallbackStdImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�T�[�o�g���C�I�[�_�擾����R�[���o�b�N�C���^�t�F�[�X�̕W������(Web3ServerTryOrderProviderCallbackStdImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
                    2005/03/17 ��(FLJ) Account�����W�����������@@�ǉ�
 */

package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.*;
import webbroker3.system.tune.affinity.util.*;

/**
 * �T�[�o�g���C�I�[�_�擾����R�[���o�b�N�C���^�t�F�[�X�̕W������
 * **/
public class WEB3ServerTryOrderProviderCallbackStdImpl
    implements
    WEB3ServerTryOrderProviderCallback
{
    /**
     * �A�J�E���gID�g���C�I�[�_�}�b�v���
     */
    private WEB3AcctIdTryOrderMapInfo acctIdTryOrderMapInfos[];

    /**
     * �A�J�E���gID�����W�g���C�I�[�_�}�b�v���
     */
    private WEB3AcctIdRangeTryOrderMapInfo acctIdRangeTryOrderMapInfos[];

    /**
     * �������ʃR�[�h�g���C�I�[�_�}�b�v���
     */
    private WEB3ReqNumTryOrderMapInfo reqNumTryOrderMapInfos[];

    /**
     * �R���X�g���N�^
     */
    public WEB3ServerTryOrderProviderCallbackStdImpl()
    {
    }

    /**
     * �����tagName��TagValue�ɑ΂��ăg���C�I�[�_��񋟂���R�[���o�b�N���\�b�h�ł��B
     *
     * @@param tagName tagValue�擾�̂��߂ɗ��p�����Request�d����Field���iXML�d���̏ꍇ�AtagName�j
     * @@param tagValue String
     * @@return ServerAccessor�̃g���C�I�[�_
     */
    public int[] getServerTryOrder(String tagName, String tagValue)
    {
        System.out.println("getServerTryOrder(String tagName, String tagValue)" + tagName +
                           "," + tagValue);
        if (SESSION_ID.equals(tagName))
        {
            long accountId = deriveAccountIdFromSessionId(tagValue);
            return getServerAccIdTryOrder(accountId);
        }
        else if (ACCOUNT_ID.equals(tagName))
        {
            if (tagValue == null)
            {
                return null;
            }
            long accountId = Long.parseLong(tagValue);
            return getServerAccIdTryOrder(accountId);
        }
        else if (ORDER_REQUEST_NUMBER.equals(tagName))
        {
            return getServerTryOrderFromRequestNumber(tagValue);
        }
        else if (ACCOUNT_ID_RANGE.equals(tagName))
        {
            return getServerAccIdRangeTryOrder(getServerAccIdRange(tagValue, true),
                                               getServerAccIdRange(tagValue, false));
        }
        else
        {
            return null;
        }

    }

    /**
     * �Z�b�V����ID����A�J�E���gID���擾����I�[�o���C�h���ꂽ���\�b�h�ł��B
     *
     * @@param tagValue �Z�b�V����ID
     * @@return �A�J�E���gID
     */
    private long deriveAccountIdFromSessionId(String sessionId)
    {
        return PasswordTools.decodeAccountId(sessionId);
    }

    /**
     * ����̃A�J�E���gID�ɑ΂��ăg���C�I�[�_��񋟂���R�[���o�b�N���\�b�h�ł��B
     *
     * @@param accountId �A�J�E���gID
     * @@return �g���C�I�[�_
     */
    private int[] getServerAccIdTryOrder(long accountId)
    {
        if (accountId == 0L)
        {
            return null;
        }
        else
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = binarySearch(acctIdTryOrderMapInfos,
                accountId);
            if (mapInfo == null)
            {
                return null;
            }
            else
            {
                return mapInfo.getAppServerTryOrder();
            }
        }
    }

    /**
     * ����̃A�J�E���gID�����W�ɑ΂��ăg���C�I�[�_��񋟂���R�[���o�b�N���\�b�h�ł��B
     *
     * @@param accountIdStart �A�J�E���g�����W�J�nID
     * @@param accountIdEnd �A�J�E���g�����W�I��ID
     * @@return �g���C�I�[�_
     */
    private int[] getServerAccIdRangeTryOrder(long accountIdStart, long accountIdEnd)
    {
        if (accountIdStart == 0L || accountIdEnd == 0L)
        {
            return null;
        }
        else
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = StdRangeSearch(
                acctIdRangeTryOrderMapInfos,
                accountIdStart, accountIdEnd);
            if (mapInfo == null)
            {
                return null;
            }
            else
            {
                return mapInfo.getAppServerTryOrder();
            }
        }
    }

    private long getServerAccIdRange(String AccIdRange, boolean isStart)
    {
        if (isStart)
        {
            return Long.parseLong(AccIdRange.substring(0, AccIdRange.indexOf(",")));
        }
        else
        {
            return Long.parseLong(AccIdRange.substring(AccIdRange.indexOf(",") + 1,
                AccIdRange.length()));
        }
    }

    /**
     * �������ʓ�2���ɑ΂��ăg���C�I�[�_��񋟂���R�[���o�b�N���\�b�h�ł��B
     *
     * @@param head2OrderRequestNumber �������ʓ�2��
     * @@return �g���C�I�[�_
     */
    private int[] getServerTryOrderFromRequestNumber(String head2OrderRequestNumber)
    {
        if (head2OrderRequestNumber == null || head2OrderRequestNumber.length() < 2)
        {
            return null;
        }
        else
        {
            for (int i = 0; i < reqNumTryOrderMapInfos.length; i++)
            {
                WEB3ReqNumTryOrderMapInfo mapInfo =
                    reqNumTryOrderMapInfos[i];
                if (head2OrderRequestNumber.equals(mapInfo.getHead2OfOrderRequestNumber()))
                {
                    return mapInfo.getAppServerTryOrder();
                }
            }
            return null;
        }
    }

    /**
     * �A�J�E���gID�g���C�I�[�_�}�b�v����ݒ肷��B
     *
     * @@param tryOrderMapInfos �A�J�E���gID�g���C�I�[�_�}�b�v���
     */
    public void setAccIdTryOrderMapInfos(WEB3AcctIdTryOrderMapInfo[]
                                         tryOrderMapInfos)
    {
        this.acctIdTryOrderMapInfos = tryOrderMapInfos;
    }

    /**
     * �A�J�E���gID�����W�g���C�I�[�_�}�b�v����ݒ肷��B
     *
     * @@param tryOrderMapInfos �A�J�E���gID�g���C�I�[�_�}�b�v���
     */
    public void setAccIdRangeTryOrderMapInfos(WEB3AcctIdRangeTryOrderMapInfo[]
                                              tryOrderMapInfos)
    {
        this.acctIdRangeTryOrderMapInfos = tryOrderMapInfos;
    }

    /**
     * �������ʃR�[�h�g���C�I�[�_�}�b�v����ݒ肷��B
     *
     * @@param tryOrderMapInfos �������ʃR�[�h�g���C�I�[�_�}�b�v���
     */
    public void setReqNumTryOrderMapInfos(
        WEB3ReqNumTryOrderMapInfo[]
        tryOrderMapInfos)
    {
        this.reqNumTryOrderMapInfos =
            tryOrderMapInfos;
    }

    private WEB3AcctIdTryOrderMapInfo StdSearch(WEB3AcctIdTryOrderMapInfo
                                                acctIdTryOrderMapInfos[],
                                                long accountId)
    {
        if (acctIdTryOrderMapInfos.length == 0)
        {
            ClientLogging.debug("acctIdTryOrderMapInfos.length == 0");
            return null;
        }

        for (int i = 0; i < acctIdTryOrderMapInfos.length; i++)
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = acctIdTryOrderMapInfos[i];
            if (accountId >= mapInfo.getAccountIdStart() &&
                accountId < mapInfo.getAccountIdEnd())
            {
                ClientLogging.debug(
                    "accountId=" + accountId + "," +
                    ">= mapInfo.getAccountIdStart()= " + mapInfo.getAccountIdStart() +
                    "," +
                    "< mapInfo.getAccountIdEnd()= " + mapInfo.getAccountIdEnd() + "," +
                    "  mapInfo.getAppServerTryOrder()= " +
                    WEB3SystemObjectPrint.print(mapInfo.getAppServerTryOrder())
                    );

                return mapInfo;
            }
        }
        return null;
    }

    private WEB3AcctIdTryOrderMapInfo StdRangeSearch(WEB3AcctIdRangeTryOrderMapInfo
        acctIdTryOrderMapInfos[],
        long accountIdStart, long accountIdEnd)
    {
        if (acctIdTryOrderMapInfos.length == 0)
        {
            ClientLogging.debug("acctIdTryOrderMapInfos.length == 0");
            return null;
        }

        for (int i = 0; i < acctIdTryOrderMapInfos.length; i++)
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = acctIdTryOrderMapInfos[i];
            if (accountIdStart >= mapInfo.getAccountIdStart() &&
                accountIdEnd <= mapInfo.getAccountIdEnd())
            {
                ClientLogging.debug(
                    "accountIdStart=" + accountIdStart + "," +
                    "accountIdEnd=" + accountIdEnd + "," +
                    "accountIdStart>= mapInfo.getAccountIdStart()= " +
                    mapInfo.getAccountIdStart() +
                    "," +
                    "accountIdEnd< mapInfo.getAccountIdEnd()= " + mapInfo.getAccountIdEnd() +
                    "," +
                    "  mapInfo.getAppServerTryOrder()= " +
                    WEB3SystemObjectPrint.print(mapInfo.getAppServerTryOrder())
                    );

                return mapInfo;
            }
        }
        return null;
    }

    private WEB3AcctIdTryOrderMapInfo binarySearch(WEB3AcctIdTryOrderMapInfo
        acctIdTryOrderMapInfos[],
        long accountId)
    {

        if (acctIdTryOrderMapInfos.length == 0)
        {
            ClientLogging.debug("acctIdTryOrderMapInfos.length == 0");
            return null;
        }

        int low = 0;
        int high = acctIdTryOrderMapInfos.length - 1;

        while (low <= high)
        {
            int mid = (low + high) >> 1;
            WEB3AcctIdTryOrderMapInfo mapInfo = acctIdTryOrderMapInfos[mid];
            if (accountId >= mapInfo.getAccountIdStart() &&
                accountId < mapInfo.getAccountIdEnd())
            {
                ClientLogging.debug(
                    "accountId=" + accountId + "," +
                    ">= mapInfo.getAccountIdStart()= " + mapInfo.getAccountIdStart() +
                    "," +
                    "< mapInfo.getAccountIdEnd()= " + mapInfo.getAccountIdEnd() + "," +
                    "  mapInfo.getAppServerTryOrder()= " +
                    WEB3SystemObjectPrint.print(mapInfo.getAppServerTryOrder())
                    );

                return mapInfo;
            }
            else if (accountId >= mapInfo.getAccountIdEnd())
            {
                low = mid + 1;
            }
            else
            {
                high = mid;
            }
        }
        return null; //not found

    }

}
@
