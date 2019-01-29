head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeTradingCalendarModelImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g��������W���[���N���X(WEB3GentradeTradingCalendarModelImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/13 �����@@�ǘa(SRA) �V�K�쐬
Revesion History : 2004/08/11 �� ��(���u) JavaDoc��ǉ�
Revesion History : 2004/08/11 �� ��(���u) get������(long)���b�\�h��ǉ�
Revesion History : 2007/12/17 ���@@�g(���u) �y���ʁz�d�l�ύX�E���f��No.297
Revesion History : 2007/12/17 ���@@�g(���u) �y���ʁz�d�l�ύX�E���f��No.306
*/
package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingCalendarModelImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
/**
 * �i�g������J�����_���f���j<BR>
 *<BR>
 * xTrade��TradingCalendarModel���g�������N���X�B<BR>
 *<BR>
 * @@author �����@@�ǘa(SRA)
 * @@version 1.0
 */
public class WEB3GentradeTradingCalendarModelImpl
    extends TradingCalendarModelImpl
{
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingCalendarModelImpl.class);

    /**
     * ����J�����_�ڍ׃N���X�̃V���O���g���C���X�^���X<BR>
     */
    private TradingCalendarDetails tradingCalendarDetails =
        new WEB3GentradeTradingClendarDetailsImpl();
    
    /**
     * ����J�����_�ڍׂ��擾����B<BR>
     *�ipublic TradingCalendarDetails getTradingCalendarDetails(long tradedProductId)<BR>
     * �̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.����J�����_�ڍׂ�ԋp����B<BR>
     * @@param l_lngTradedProductId - �������ID<BR>
     * @@return TradingCalendarDetails <BR>
     */
    public TradingCalendarDetails getTradingCalendarDetails(long l_lngTradedProductId)
    {
        return this.tradingCalendarDetails;
    }
    
    /**
     * ����J�����_�ڍׂ��擾����B<BR>
     *�ipublic TradingCalendarDetails getTradingCalendarDetails(Market l_market)<BR>
     * �̃I�[�o�[���C�h�j<BR>
     *<BR>
     * this.����J�����_�ڍׂ�ԋp����B<BR>
     * @@param l_market - �s��I�u�W�F�N�g�B<BR>
     * @@return TradingCalendarDetails <BR>
     */
    public TradingCalendarDetails getTradingCalendarDetails(Market l_market)
    {
        return this.tradingCalendarDetails;
    }

    /** 
     * ���������擾����B<BR>
     *�igetCurrentBizDate(long tradedProductId�j�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �P�j�@@TradedProductDao.findRowByPk( )���A�������Row���擾����B<BR>
     * �@@�@@�@@�mfindRowByPk( )�̈����n<BR>
     * �@@�@@�@@�@@�������ID�F�@@�����̎������ID<BR>
     * <BR>
     * �Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket( )���A�s��I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�mgetMarket( )�̈����n<BR>
     * �@@�@@�@@�@@�s��ID�F�@@�P�j�Ŏ擾�����������Row.�s��ID<BR>
     * <BR>
     * �R�j�@@�s��.isPTS�s��( )���R�[�����A�߂�l�ɂ��ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     * �@@�R�|�P�j�@@true�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@������ԊǗ�.getPTS������(void)��delegate����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@false�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@������ԊǗ�.get������(void)��delegate����B<BR>
     * @@param l_lngTradedProductId - �������ID<BR>
     * @@return Date <BR>
     */
    public Date getCurrentBizDate(long l_lngTradedProductId)
    {
        final String STR_METHOD_NAME = "getCurrentBizDate(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@TradedProductDao.findRowByPk( )���A�������Row���擾����B
        //�mfindRowByPk( )�̈����n
        //�������ID�F�@@�����̎������ID
        boolean l_blnIsPTSMarket = false;
        WEB3GentradeMarket l_market = null;
        try
        {
            TradedProductRow l_tradedProductRow =
                TradedProductDao.findRowByPk(l_lngTradedProductId);

            //�Q�j�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket( )���A�s��I�u�W�F�N�g���擾����B
            //�mgetMarket( )�̈����n
            //�s��ID�F�@@�P�j�Ŏ擾�����������Row.�s��ID
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_genFinObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            l_market =
                (WEB3GentradeMarket)l_genFinObjectManager.getMarket(l_tradedProductRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        try
        {
            //�R�j�@@�s��.isPTS�s��( )���R�[�����A�߂�l�ɂ��ȉ��̃��\�b�h���R�[������B
            l_blnIsPTSMarket = l_market.isPTSMarket();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("DB�T�[�o�Ƃ̒ʐM�Ɏ��s����", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        try
        {
            if (l_blnIsPTSMarket)
            {
                //�R�|�P�j�@@true�̏ꍇ
                //������ԊǗ�.getPTS������(void)��delegate����B
                return WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();
            }
            else
            {
                //�R�|�Q�j�@@false�̏ꍇ
                //������ԊǗ�.get������(void)��delegate����B
                return WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
        }
        catch (WEB3SystemLayerException wse)
        {
            log.error(wse.getErrorMessage(), wse);
            throw new WEB3BaseRuntimeException(
                wse.getErrorInfo(),
                this.getClass().getName() + ".getCurrentBizDate()",
                wse.getMessage(),
                wse);

        }
    }
}
@
