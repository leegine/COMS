head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����M����ForMock(WEB3MutualFundProductForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.FrgnMmfExchangeRateParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * �g�����M����ForMock
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3MutualFundProductForMock extends WEB3MutualFundProduct
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductForMock.class);

    /**
     * (�g�����M����(Mock))<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * super(����Row)���R�[������B<BR>
     * @@param l_productRow - ����Row
     */
    public WEB3MutualFundProductForMock(ProductRow l_productRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_productRow);
    }

    /**
     * (�g�����M����(Mock))<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * super(���M����Row)���R�[������B<BR>
     * @@param l_mutualFundProductRow - ���M����Row
     */
    public WEB3MutualFundProductForMock(MutualFundProductRow l_mutualFundProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundProductRow);
    }

    /**
     *(get�O��MMF�בփ��[�g(Mock))<BR>
     * @@return �O��MMF�בփ��[�gParams <BR>
     * @@throws WEB3BaseException 
     */
    public FrgnMmfExchangeRateParams getFrgnMmfExchangeRate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getFrgnMmfExchangeRate()-->ForMock";
        log.entering(STR_METHOD_NAME);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProduct",
            "getFrgnMmfExchangeRate",
            new Class[] {}))
        {
            log.exiting(STR_METHOD_NAME);
            //1�jMockFor --�r asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProduct",
                "getFrgnMmfExchangeRate",
                new Class[] {}).asWEB3BaseException();

            //2)MockFor --�r asObject
            return(FrgnMmfExchangeRateParams)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProduct",
                "getFrgnMmfExchangeRate",
                new Class[] {}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.getFrgnMmfExchangeRate();
    }

    /**
     * (is���t�����L��(Mock))<BR>
     * �������ɔ��t�������ۂ����Ă��邩�ǂ����𔻒肷��B<BR>
     * <BR>
     * this.getDataSourceObject().get���t�����敪( )�̖߂�l��<BR>
     * "0�F���t�\"�̏ꍇ�Afalse��ԋp����B<BR>
     * this.getDataSourceObject().get���t�����敪( )�̖߂�l��<BR>
     * "1�F�抷���t�̂݉\"�̏ꍇ�Atrue��ԋp����B<BR>
     * @@return boolean
     */
    public boolean isAcquiredDeregExistence()
    {
        final String STR_METHOD_NAME = "isAcquiredDeregExistence()-->ForMock";
        log.entering(STR_METHOD_NAME);

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.mf.WEB3MutualFundProduct",
            "isAcquiredDeregExistence",
            new Class[] {}))
        {
            log.exiting(STR_METHOD_NAME);
            //1�jMockFor --�r asWEB3BaseException

            //2)MockFor --�r asObject
            return(boolean)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProduct",
                "isAcquiredDeregExistence",
                new Class[] {}).asBoolean();
        }

        log.exiting(STR_METHOD_NAME);
        return super.isAcquiredDeregExistence();

    }
    public String getStandardName()
    {
        throw new UnsupportedOperationException("getStandardName�͗��p�ł��Ȃ��I�`�[�����[�_�֘A�����Ă��������I");
    }
}
@
