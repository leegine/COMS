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
filename	WEB3GentradeBranchListmarketDealtCond.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i���X�s����敪�ʁj�戵����(WEB3GentradeBranchListmarketDealtCond.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/06 �h�C(���u) �V�K�쐬�y���ʁz�d�l�ύX�E���f��No.261
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.BranchListmarketDealtCondDao;
import webbroker3.gentrade.data.BranchListmarketDealtCondPK;
import webbroker3.gentrade.data.BranchListmarketDealtCondRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�i���X�s����敪�ʁj�戵����)<BR>
 * DB���C�A�E�g �u�i���X�s����敪�ʁj�戵�����e�[�u��.xls�v�Q��<BR>
 * <BR>
 * @@author �h�C(���u)
 * @@version 1.0
 */
public class WEB3GentradeBranchListmarketDealtCond implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log
        = WEB3LogUtility.getInstance(WEB3GentradeBranchListmarketDealtCond.class);

    /**
     * ((���X�s����敪��)�戵����Row)<BR>
     * �i���X�s����敪�ʁj�戵����Row�I�u�W�F�N�g  <BR>
     * �iDAO���������N���X�j<BR>
     */
    private BranchListmarketDealtCondRow branchListmarketDealtCondRow;

    /**
     * ((���X�s����敪��)�戵����)<BR>
     * <BR>
     * �R���X�g���N�^�B  <BR>
     * �����̏����Ɉ�v����i���X�s����敪�ʁj�戵�����I�u�W�F�N�g��ԋp����B  <BR>
     * <BR>
     * �P�j�@@DB����  <BR>
     * �@@�����̒l�ɂāi���X�s����敪�ʁj�戵�����e�[�u������������B  <BR>
     * <BR>
     * �@@���s�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ�͗�O���X���[����B <BR>
     * <BR>
     * �Q�j�@@�s�I�u�W�F�N�g�Z�b�g  <BR>
     * �@@�������ʂ̍s�I�u�W�F�N�g�i�i���X�s����敪�ʁj�戵����Row�j�� <BR>
     * �@@this.�i���X�s����敪�ʁj�戵�����ɃZ�b�g����B <BR>
     * <BR>
     * @@param l_lngBranchId - ���XID
     * @@param l_lngMarketId - �s��ID
     * @@param l_strListType - ���敪
     * @@param l_strNewListType - �V�s��敪
     * @@param l_strOpenOtcDiv - �X�����J�敪
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeBranchListmarketDealtCond(
        long l_lngBranchId,
        long l_lngMarketId,
        String l_strListType,
        String l_strNewListType,
        String l_strOpenOtcDiv)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "WEB3GentradeBranchListmarketDealtCond(long, long, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            BranchListmarketDealtCondPK l_branchListmarketDealtCondPK
                = new BranchListmarketDealtCondPK(
                    l_lngBranchId,
                    l_lngMarketId,
                    l_strListType,
                    l_strNewListType,
                    l_strOpenOtcDiv);

            this.branchListmarketDealtCondRow
                = BranchListmarketDealtCondDao.findRowByPk(l_branchListmarketDealtCondPK);
        }
        catch (DataFindException l_dfe)
        {
            log.error(l_dfe.getMessage(), l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(l_dqe.getMessage(), l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(l_dne.getMessage(), l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ((���X�s����敪��)�戵����)<BR>
     * <BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �{�I�u�W�F�N�g���C���X�^���X�����A <BR>
     * �����̍s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_branchListmarketDealtCondRow - (���X�s����敪��)�戵����Row<BR>
     * (���X�s����敪��)�戵�����s�I�u�W�F�N�g<BR>
     * @@throws WEB3SystemLayerException
     */
    public WEB3GentradeBranchListmarketDealtCond(
        BranchListmarketDealtCondRow l_branchListmarketDealtCondRow)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME
            = "WEB3GentradeBranchListmarketDealtCond(BranchListmarketDealtCondRow)";
        log.entering(STR_METHOD_NAME);

        if (l_branchListmarketDealtCondRow == null)
        {
            log.debug("(���X�s����敪��)�戵�����s�I�u�W�F�N�g = null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "(���X�s����敪��)�戵�����s�I�u�W�F�N�g = null");
        }

        this.branchListmarketDealtCondRow = l_branchListmarketDealtCondRow;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���P��������l)<BR>
     * <BR>
     * �����̌ڋq.�����^�C�v�ɑΉ��������P��������l��ԋp����B <BR>
     * �@@ <BR>
     * �@@�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ <BR>
     * �@@�@@this.���P��������l(�@@�l)��ԋp����B <BR>
     * <BR>
     * �@@�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ <BR>
     * �@@�@@this.���P��������l(�l)��ԋp����B<BR>
     * @@param l_mainAccount - �ڋq
     * @@return Double
     * @@throws WEB3SystemLayerException
     */
    public Double getMaxContUnit(WEB3GentradeMainAccount l_mainAccount) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMaxContUnit(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�ڋq = null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq = null");
        }

        //�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ
        //this.���P��������l(�@@�l)��ԋp����B
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccount.getMainAccountRow().getAccountType()))
        {
            if (this.branchListmarketDealtCondRow.getMaxContUnitCorpIsNull())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return new Double(this.branchListmarketDealtCondRow.getMaxContUnitCorp());
        }

        //�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ
        //this.���P��������l(�l)��ԋp����B
        if (this.branchListmarketDealtCondRow.getMaxContUnitIndIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return new Double(this.branchListmarketDealtCondRow.getMaxContUnitInd());
    }

    /**
     * (get���������l)<BR>
     * <BR>
     * �����̌ڋq.�����^�C�v�ɑΉ��������������l��ԋp����B <BR>
     * <BR>
     * �@@�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ <BR>
     * �@@�@@this.���������l(�@@�l)��ԋp����B <BR>
     * <BR>
     * �@@�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ <BR>
     * �@@�@@this.���������l(�l)��ԋp����B<BR>
     * @@param l_mainAccount - �ڋq
     * @@return Double
     * @@throws WEB3SystemLayerException
     */
    public Double getMaxContPrice(WEB3GentradeMainAccount l_mainAccount) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMaxContPrice(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�ڋq = null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq = null");
        }

        //�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ
        //this.���������l(�@@�l)��ԋp����B
        if (MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccount.getMainAccountRow().getAccountType()))
        {
            if (this.branchListmarketDealtCondRow.getMaxContPriceCorpIsNull())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            log.exiting(STR_METHOD_NAME);
            return new Double(this.branchListmarketDealtCondRow.getMaxContPriceCorp());
        }

        //�ڋq.�����^�C�v �� �@@�l�A�J�E���g�̏ꍇ
        //this.���������l(�l)��ԋp����B
        if (this.branchListmarketDealtCondRow.getMaxContPriceIndIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return new Double(this.branchListmarketDealtCondRow.getMaxContPriceInd());
    }

    /**
     * (get������K����)<BR>
     * <BR>
     * ������K������ԋp����B <BR>
     * <BR>
     * [a.this.(���X�s����敪��)�戵����Row.������K���� == null�@@] <BR>
     * <BR>
     * �@@�ԋp�l�Fnull <BR>
     * <BR>
     * [a.this.(���X�s����敪��)�戵����Row.������K���� �� null�@@] <BR>
     * <BR>
     * �@@�ԋp�l�Fnew Double(this.(���X�s����敪��)�戵����Row.������K����)<BR>
     * @@return Double
     */
    public Double getMarginSecCheckRate()
    {
        final String STR_METHOD_NAME = "getMarginSecCheckRate()";
        log.entering(STR_METHOD_NAME);

        if (this.branchListmarketDealtCondRow.getMarginSecCheckRateIsNull())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return new Double(this.branchListmarketDealtCondRow.getMarginSecCheckRate());
    }

    /**
     * this.(���X�s����敪��)�戵����Row��ԋp����B<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.branchListmarketDealtCondRow;
    }
}
@
