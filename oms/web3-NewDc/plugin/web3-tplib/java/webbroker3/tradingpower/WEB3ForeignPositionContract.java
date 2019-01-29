head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3ForeignPositionContract.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݎc�����(WEB3ForeignPositionContract.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/03 �����F (���u) �V�K�쐬 �d�l�ύX���f��122
Revision History : 2007/08/07 ���� (���u)�@@�d�l�ύX���f��152
Revision History : 2007/08/17 ���� (���u)�@@�d�l�ύX���f��165
*/
package webbroker3.tradingpower;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.data.TpCashBalanceFrgnParams;
import webbroker3.tradingpower.data.TpCashBalanceFrgnRow;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * �O�ݎc�����<BR>
 * (�O�ݎc�����)<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3ForeignPositionContract
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ForeignPositionContract.class);

    /**
     * (�O�ݎc��Params)<BR>
     * �O�ݎc��Params<BR>
     */
    protected TpCashBalanceFrgnParams tpCashBalanceFrgnParams;

    /**
     * (�O�ݎc�����)<BR>
     * �R���X�g���N�^<BR>
     */
    public WEB3ForeignPositionContract()
    {

    }

    /**
     * (get�O�ݎc��Params)<BR>
     * get�O�ݎc��Params<BR>
     * <BR>
     * this.�O�ݎc��Params��ԋp����B<BR>
     * @@return TpCashBalanceFrgnParams
     */
    public TpCashBalanceFrgnParams getTpCashBalanceFrgnParams()
    {
        return tpCashBalanceFrgnParams;
    }

    /**
     * (set�O�ݎc��Params)<BR>
     * set�O�ݎc��Params<BR>
     * <BR>
     * �p�����[�^.�O�ݎc��Params��this.�O�ݎc��Params�ɃZ�b�g����B<BR>
     * @@param l_tpCashBalanceFrgnParams - (�O�ݎc��Params)<BR>
     * �O�ݎc��Params<BR>
     */
    public void setTpCashBalanceFrgnParams(TpCashBalanceFrgnParams l_tpCashBalanceFrgnParams)
    {
        this.tpCashBalanceFrgnParams = l_tpCashBalanceFrgnParams;
    }

    /**
     * (get�O�ݎc��)<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�w���(=n)�́A�uget�O�ݎc���v��ԋp����B <BR>
     * <BR>
     * �P�j�@@null�`�F�b�N���s���B <BR>
     * this.�O�ݎc��Params��null�̎��Anull��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�����`�F�b�N���s���B <BR>
     * ������0�ȏ�5�ȉ��łȂ����A0��ԋp����B <BR>
     * <BR>
     * �R�j�@@�����Ŏw�肳�ꂽ�w���(=n)�́u�O�ݎc���v��ԋp����B <BR>
     * �m�ԋp�l�n <BR>
     * this.�O�ݎc��Params.get�O�ݎc���iT+n�j<BR>
     * @@param l_intSpecifiedPoint - (�w���)<BR>
     * �w���<BR>
     * @@return Double
     */
    public Double getForeignPositionBalance(int l_intSpecifiedPoint)
    {
        final String STR_METHOD_NAME = "getForeignPositionBalance(int)";
        log.entering(STR_METHOD_NAME);

        //�����Ŏw�肳�ꂽ�w���(=n)�́A�u�O�ݎc���v��ԋp����B
        //null�`�F�b�N���s���B
        //this.�O�ݎc��Params��null�̎��Anull��ԋp����B
        if (this.tpCashBalanceFrgnParams == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        double l_dblCashBalance = 0;

        switch (l_intSpecifiedPoint)
        {
            case WEB3TPSpecifiedPointDef.T_0 :
                //get�O�ݎc��( T + 0 )���擾����B
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn0();
                break;

            case WEB3TPSpecifiedPointDef.T_1 :
                //get�O�ݎc��( T + 1 )���擾����B
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn1();
                break;

            case WEB3TPSpecifiedPointDef.T_2 :
                //get�O�ݎc��( T + 2 )���擾����B
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn2();
                break;

            case WEB3TPSpecifiedPointDef.T_3 :
                //get�O�ݎc��( T + 3 )���擾����B
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn3();
                break;

            case WEB3TPSpecifiedPointDef.T_4 :
                //get�O�ݎc��( T + 4 )���擾����B
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn4();
                break;

            case WEB3TPSpecifiedPointDef.T_5 :
                //get�O�ݎc��( T + 5 )���擾����B
                l_dblCashBalance = this.tpCashBalanceFrgnParams.getCashBalanceFrgn5();
                break;
        }

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblCashBalance);
    }

    /**
     * (create�O�ݎc�����)<BR>
     * (static���\�b�h)(create�O�ݎc�����) <BR>
     * <BR>
     * �P�j�O�ݎc�����I�u�W�F�N�g�𐶐�����B <BR>
     * <BR>
     * �@@�|�R���X�g���N�^�A�O�ݎc�����()�R�[��  <BR>
     * <BR>
     * <BR>
     * �Q�j�ڋq����c���i�}�X�^���j�i�O�݁j�e�[�u�����擾 <BR>
     * <BR>
     * �@@�|�ڋq����c���i�}�X�^���j�i�O�݁j�e�[�u�����ȉ��̏����Ō�������B <BR>
     * �@@�@@�m���������n <BR>
     * �@@�@@�@@����ID�F����.����ID <BR>
     * �@@�@@�@@�ʉ݃R�[�h�F����.�ʉ݃R�[�h <BR>
     * <BR>
     * �@@�@@[a.�������� == null �܂��� ��������.size() == 0 �̏ꍇ]  <BR>
     * �@@�@@�@@�O�ݎc��Params = null  <BR>
     * <BR>
     * �@@�@@[��.a�ȊO �̏ꍇ]  <BR>
     * �@@�@@�@@�O�ݎc��Params = �i�O�ݎc��Params�j��������.get(0)  <BR>
     * <BR>
     * <BR>
     * �R�j���������O�ݎc�����I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * @@return WEB3ForeignPositionContract
     * @@throws WEB3SystemLayerException
     */
    public static WEB3ForeignPositionContract createForeignPositionContract(
        long l_lngAccountId,
        String l_strCurrencyCode) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "createForeignPositionContract(long, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�O�ݎc�����I�u�W�F�N�g�𐶐�����B
        WEB3ForeignPositionContract l_foreignPositionContract = new WEB3ForeignPositionContract();

        // �Q�j�ڋq����c���i�}�X�^���j�i�O�݁j�e�[�u�����擾
        try
        {

            // �m���������n
            // �@@�@@ ����ID�F����.����ID
            //�@@�@@�@@�ʉ݃R�[�h�F����.�ʉ݃R�[�h
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisTpCashBalanceFrgnRows = l_queryProcessor.doFindAllQuery(
                TpCashBalanceFrgnRow.TYPE,
                "account_id=? and currency_code=?",
                new Object[]{new Long(l_lngAccountId), l_strCurrencyCode});

            // �������� == null �܂��� ��������.size() == 0 �̏ꍇ
            if (l_lisTpCashBalanceFrgnRows == null || l_lisTpCashBalanceFrgnRows.size() == 0)
            {
                l_foreignPositionContract.tpCashBalanceFrgnParams = null;
            }

            // �ȊO �̏ꍇ
            else
            {
                l_foreignPositionContract.tpCashBalanceFrgnParams =
                    new TpCashBalanceFrgnParams((TpCashBalanceFrgnRow)l_lisTpCashBalanceFrgnRows.get(0));
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_foreignPositionContract;
    }
}
@
