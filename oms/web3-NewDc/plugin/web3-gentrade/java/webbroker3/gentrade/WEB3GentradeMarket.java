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
filename	WEB3GentradeMarket.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s��(WEB3GentradeMarket)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/05 ����@@���j(SRA) �V�K�쐬
Revesion History : 2004/02/17 ����@@���j(SRA) ����
Revesion History : 2005/07/07 �Г� (���u) isSuspension()��ǉ�
Revesion History : 2005/07/07 �Г� (���u) isSystemInterLock()��ǉ�
Revesion History : 2006/12/25 �h�C(���u) �d�l�ύX ���f��219��Ή�
Revesion History : 2007/12/17 �đo�g (���u) �d�l�ύX ���f��No.284,No.300
Revesion History : 2010/01/12 ��іQ (���u)�y���ʁz�d�l�ύX�E���f��No.349
**/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.MarketImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EquityPTSMarketDivDef;
import webbroker3.common.define.WEB3FeqDayTradeMarketDivDef;
import webbroker3.common.define.WEB3FeqOrderRequestDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3SuspensionDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�s��)<BR>
 */
public class WEB3GentradeMarket
    extends MarketImpl
    implements WEB3GentradeMarketValues
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMarket.class);

    /**
     * �R���X�g���N�^<BR>
     *<BR> 
     * @@param long l_lngMarketId
     * @@roseuid 400FA00200B2
     */
    public WEB3GentradeMarket(long l_lngMarketId)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_lngMarketId);
    }

    /**
     * �R���X�g���N�^<BR>
     *<BR> 
     * @@param Institution l_institution
     * @@param String l_strMarketCode
     */
    public WEB3GentradeMarket(
        Institution l_institution,
        String l_strMarketCode)
        throws DataFindException, DataQueryException, DataNetworkException
    {
        super(l_institution, l_strMarketCode);
    }

    /**
     * (is�����~)<BR>
     * �s�ꂪ�����~���𔻒肷��B<BR>
     * <BR>
     * �ithis.�s��s.�����~ == 1�F�����~���j�̏ꍇ�Atrue��ԋp����B<BR>
     * �ȊO�Afalse��ԋp����B<BR> 
     * <BR>
     * @@return boolean
     */
    public boolean isSuspension()
    {
        final String STR_METHOD_NAME = "isSuspension()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnSuspension = false;
        String l_strSuspension = null;

        MarketRow l_marketRow = (MarketRow)(this.getDataSourceObject());
        l_strSuspension = l_marketRow.getSuspension();
        
        if(WEB3SuspensionDef.SUSPENSION.equals(l_strSuspension)) 
        {
            l_blnSuspension = true;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnSuspension;
    }

    /**
     * (is�V�X�e���A�� )<BR>
     * �Y���̎s�ꂪ�V�X�e���A�����Ă��邩�𔻒肷��B<BR>
     * this.�s��s.�O�������s��A���敪 == �h��A���h �̏ꍇ�A<BR>
     * false��ԋp����B<BR> 
     * �ȊO�Atrue��ԋp����B<BR> 
     * <BR>
     * @@return boolean
     */
    public boolean isSystemInterLock()
    {
        final String STR_METHOD_NAME = "isSystemInterLock()";
        log.entering(STR_METHOD_NAME);

        boolean l_blnInterLock = true;
        String l_strRequestDiv = null;

        MarketRow l_marketRow = (MarketRow)(this.getDataSourceObject());
        l_strRequestDiv = l_marketRow.getFeqOrderRequestDiv();

        if(WEB3FeqOrderRequestDivDef.REQUEST_MAIL.equals(l_strRequestDiv)) 
        {
            l_blnInterLock = false;
        }

        log.exiting(STR_METHOD_NAME);

        return l_blnInterLock;
    }
    /**
     * (is�D��s��R�[�h)<BR>
     * �w�肳�ꂽ�s��R�[�h���D��s��R�[�h���𔻒肷��B <BR>
     * <BR>
     * �P�j�@@�����̎s��R�[�h��null�̏ꍇ�A <BR>
     * �@@�@@false��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�����̎s��R�[�h��"99�F�D��s��"�̏ꍇ�A <BR>
     * �@@�@@true��ԋp����B <BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ�A <BR>
     * �@@�@@false��ԋp����B <BR>
     * <BR>
     * @@param l_strMarketCode �s��R�[�h
     * @@return boolean
     */

    public static boolean isPriorityMarket(String l_strMarketCode)
    {
        boolean l_blnPriorityMarket = false;

        //�����̎s��R�[�h��null�̏ꍇ�Afalse��ԋp����B
        if (l_strMarketCode == null)
        {
            l_blnPriorityMarket = false;
        }

        //�����̎s��R�[�h��"99�F�D��s��"�̏ꍇ�Atrue��ԋp����B
        else if (WEB3MarketCodeDef.PRIORITY_MARKET.equals(l_strMarketCode))
        {
            l_blnPriorityMarket = true;
        }

        //��L�ȊO�̏ꍇ�Afalse��ԋp����B
        return l_blnPriorityMarket;
    }

    /**
     * (isPTS�s��)<BR>
     * PTS�s��ł��邩���肷��B<BR>
     * PTS�s��̏ꍇ�Atrue���APTS�s��łȂ��ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j�@@�s��p�v���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�s��ID = this.getMarketId()�̖߂�l<BR>
     * �@@�@@�@@�v���t�@@�����X���ږ� = �v���t�@@�����X��.PTS�s��敪<BR>
     * �@@�@@�@@���ږ��A�� = 1<BR>
     * <BR>
     * �Q�j�@@�擾���R�[�h.�v���t�@@�����X�̒l��"PTS�s��ł���"�ꍇ�A<BR>
     * �@@�@@�@@true��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@��L�ȊO�A�܂��͊Y�����R�[�h�����݂��Ȃ��ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isPTSMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isPTSMarket()";
        log.entering(STR_METHOD_NAME);

        MarketPreferencesRow l_marketPreferencesRow;

        try
        {
            //�s��p�v���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����
            //[����]
            // �s��ID = this.getMarketId()�̖߂�l
            // �v���t�@@�����X���ږ� = �v���t�@@�����X��.PTS�s��敪
            // ���ږ��A�� = 1
            l_marketPreferencesRow = MarketPreferencesDao.findRowByPk(
                this.getMarketId(),
                WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV,
                1);
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        String l_strPtsMarketDiv = l_marketPreferencesRow.getValue();
        if (WEB3EquityPTSMarketDivDef.PTS_MARKET_YES.equals(l_strPtsMarketDiv))
        {
            //�擾���R�[�h.�v���t�@@�����X�̒l��"PTS�s��ł���"�ꍇ�Atrue��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //PTS�s��łȂ��ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is���v��s��)<BR>
     * ���v��s��ł��邩���肷��B<BR>
     * ���v��s��̏ꍇ�Atrue���A���v��s��łȂ��ꍇ�Afalse��ԋp����B<BR>
     * <BR>
     * �P�j�@@�s��p�v���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�s��ID = this.getMarketId()�̖߂�l<BR>
     * �@@�@@�@@�v���t�@@�����X���ږ� = �v���t�@@�����X��.�O�����v��s��敪<BR>
     * �@@�@@�@@���ږ��A�� = 1<BR>
     * <BR>
     * �Q�j�@@�擾���R�[�h.�v���t�@@�����X�̒l��"�O�����v��s��ł���"�ꍇ�A<BR>
     * �@@�@@�@@true��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@��L�ȊO�A�܂��͊Y�����R�[�h�����݂��Ȃ��ꍇ�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDayTradeMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDayTradeMarket()";
        log.entering(STR_METHOD_NAME);

        MarketPreferencesRow l_marketPreferencesRow = null;
        //�P�j�@@�s��p�v���t�@@�����X�e�[�u������ȉ������S�ĂɊY�����郌�R�[�h���擾����B
        try
        {
            l_marketPreferencesRow =
                MarketPreferencesDao.findRowByMarketIdNameNameSerialNo(
                    this.getMarketId(),
                    WEB3MarketPreferencesNameDef.FEQ_DAY_TRADE_MARKET_DIV,
                    1);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�@@�擾���R�[�h.�v���t�@@�����X�̒l��"�O�����v��s��ł���"�ꍇ�A�A
        //true��ԋp����B
        if (l_marketPreferencesRow != null
            && WEB3FeqDayTradeMarketDivDef.EXECUTE.equals(l_marketPreferencesRow.getValue()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        //��L�ȊO�A�܂��͊Y�����R�[�h�����݂��Ȃ��ꍇ�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
