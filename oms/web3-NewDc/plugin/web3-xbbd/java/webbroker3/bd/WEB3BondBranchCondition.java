head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBranchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����X�ʏ���(WEB3BondBranchCondition.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/30 �ęԍg (���u) �V�K�쐬
                   2006/10/26 ���G�� (���u)�@@�c�a���C�A�E�g No.025
Revesion History : 2007/7/26 ������ (���u) �d�l�ύX�E���f��No.233
*/
package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����X�ʏ���)<BR>
 * �����X�ʏ����N���X <BR>
 *
 * @@author �ęԍg(���u)
 * @@version 1.0
 */
public class WEB3BondBranchCondition
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBranchCondition.class);

    /**
     * (�����X�ʏ����s)<BR>
     * �����X�ʏ����s�I�u�W�F�N�g <BR>
     */
    private BondBranchConditionRow bondBranchConditionRow;

    /**
     * (�����X�ʏ���)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �P�j�ȉ��̏����ŁA�����X�ʏ����e�[�u�����烌�R�[�h���擾����B<BR>
     * <BR>
     * �@@[�擾����] <BR>
     * �@@�@@���XID = ����.���XID<BR>
     *  <BR>
     * �Q�j�擾�������R�[�h��this.�����X�ʍs�ɃZ�b�g����B<BR>
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID<BR>
     * @@throws WEB3BaseException
     */
    public WEB3BondBranchCondition(long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3BondBranchCondition(long)";
        log.entering(STR_METHOD_NAME);

        //�P�j�ȉ��̏����ŁA�����X�ʏ����e�[�u�����烌�R�[�h���擾����
        BondBranchConditionRow l_bondBranchConditionRow = null;
        try
        {
            l_bondBranchConditionRow = BondBranchConditionDao.findRowByPk(l_lngBranchId);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�A�N�Z�X�����s�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�����s�̏ꍇ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j�擾�������R�[�h��this.�����X�ʍs�ɃZ�b�g����
        this.bondBranchConditionRow = l_bondBranchConditionRow;
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�����{�敪)<BR>
     * �����{�敪���擾����B<BR>
     * <BR>
     * this.�����X�ʏ����s.get�����{�敪()�̖߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getEnforceDiv()
    {
        final String STR_METHOD_NAME = " getEnforceDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getEnforceDiv();
    }

    /**
     * (get�ۗL���Y�`�F�b�N�敪)<BR>
     * �ۗL���Y�`�F�b�N�敪���擾����B<BR>
     * <BR>
     * this.�����X�ʏ����s.get�ۗL���Y�`�F�b�N�敪()�̖߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getAssetCheckDiv()
    {
        final String STR_METHOD_NAME = " getAssetCheckDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getAssetCheckDiv();
    }

    /**
     * (get�������b�N�g�p�敪)<BR>
     * �������b�N�g�p�敪���擾����B<BR>
     * <BR>
     * this.�����X�ʏ����s.get�������b�N�g�p�敪()�̖߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getOrderLockUseDiv()
    {
        final String STR_METHOD_NAME = " getOrderLockUseDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getOrderLockUseDiv();
    }

    /**
     * (get�������ݒ�敪)<BR>
     * �������ݒ�敪���擾����B<BR>
     * <BR>
     * this.�����X�ʏ����s.get�������ݒ�敪()�̖߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getPaymentDateSetDiv()
    {
        final String STR_METHOD_NAME = " getPaymentDateSetDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getPaymentDateSetDiv();
    }

    /**
     * (get����g���X�ʊǗ��敪)<BR>
     * ����g���X�ʊǗ��敪���擾����B<BR>
     * <BR>
     * this.�����X�ʏ����s.get����g���X�ʊǗ��敪()�̖߂�l��ԋp����B<BR>
     * @@return String
     */
    public String getBranchRecruitLimitDiv()
    {
        final String STR_METHOD_NAME = " getBranchRecruitLimitDiv()";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return this.bondBranchConditionRow.getBranchRecruitLimitDiv();
    }
}
@
