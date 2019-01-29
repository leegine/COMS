head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStopInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��~��񃁃b�Z�[�W(WEB3AccInfoStopInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.accountinfo.define.WEB3LockDivDef;
import webbroker3.accountinfo.define.WEB3OrderPermitDivDef;
import webbroker3.accountinfo.define.WEB3YellowAccountDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (��~���)<BR>
 * ��~��񃁃b�Z�[�W<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoStopInfo extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoStopInfo.class);
        
    /**
     * (�x�q�敪)<BR>
     * �x�q�敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�x�q�łȂ��j<BR>
     * 1�F�@@�x�q<BR>
     * <BR>
     */
    public String yellowAccountDiv;
    
    /**
     * (�Ǘ����b�N�敪)<BR>
     * �Ǘ����b�N�敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i���b�N�łȂ��j<BR>
     * 1�F�@@���b�N��<BR>
     */
    public String mngLockDiv;
    
    /**
     * (�Ǘ����b�N���R�t���O�i���֋��j)<BR>
     * �Ǘ����b�N���R�t���O�i���֋��j<BR>
     * <BR>
     * true�F�@@���R����<BR>
     * false�F�@@���R�Ȃ�<BR>
     * <BR>
     */
    public boolean mngExpenseLockReasonFlag;
    
    /**
     * (�Ǘ����b�N���R�t���O�i�ۏ؋������j)<BR>
     * �Ǘ����b�N���R�t���O�i�ۏ؋������j<BR>
     * <BR>
     * true�F�@@���R����<BR>
     * false�F�@@���R�Ȃ�<BR>
     */
    public boolean mngDepositLockReasonFlag;
    
    /**
     * (�Ǘ����b�N���R�t���O�i�K�i�S�ەs���j)<BR>
     * �Ǘ����b�N���R�t���O�i�K�i�S�ەs���j<BR>
     * <BR>
     * true�F�@@���R����<BR>
     * false�F�@@���R�Ȃ�<BR>
     * <BR>
     */
    public boolean mngCollateralLockReasonFlag;
    
    /**
     * (�Ǘ����b�N���R�t���O�i�a��ؒ��������ցj)<BR>
     * �Ǘ����b�N���R�t���O�i�a��ؒ��������ցj<BR>
     * <BR>
     * true�F�@@���R����<BR>
     * false�F�@@���R�Ȃ�<BR>
     * <BR>
     */
    public boolean mngReceiptLockReasonFlag;
    
    /**
     * (�Ǘ����b�N�����J�n��)<BR>
     * �Ǘ����b�N�����J�n��<BR>
     */
    public Date mngLockCancelStartDate;
    
    /**
     * (�Ǘ����b�N�����I����)<BR>
     * �Ǘ����b�N�����I����<BR>
     */
    public Date mngLockCancelEndDate;
    
    /**
     * (�x�X���b�N�敪)<BR>
     * �x�X���b�N�敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i���b�N�łȂ��j<BR>
     * 1�F�@@���b�N��<BR>
     */
    public String branchLockDiv;
    
    /**
     * (�����F�敪)<BR>
     * �����F�敪<BR>
     * <BR>
     * 0�F�@@�F��<BR>
     * 1�F�@@��F��<BR>
     */
    public String orderPermitDiv;
    
    /**
     * (�󋵋敪)<BR>
     * �󋵋敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�֎~���łȂ��j<BR>
     * 1�F�@@�֎~��<BR>
     */
    public String stateDiv;
    
    /**
     * (��~�󋵓o�^���R)<BR>
     * ��~�󋵓o�^���R<BR>
     */
    public String stopStateRegistReason;
    
    /**
     * (Y�q�o�^����SONAR��t��)<BR>
     * Y�q�o�^����SONAR��t�� <BR>
     * <BR>
     * 0�F�����M <BR>
     * 1�F���M�� <BR>
     * 9�F�G���[ <BR>
     * null�F�f�[�^�Ȃ� <BR>
     */
    public String yAccountSonarState;
    
    /**
     * (���b�N�q�o�^����SONAR��t��)<BR>
     * ���b�N�q�o�^����SONAR��t�� <BR>
     * <BR>
     * 0�F�����M <BR>
     * 1�F���M�� <BR>
     * 9�F�G���[ <BR>
     * null�F�f�[�^�Ȃ� <BR>
     */
    public String lockAccountSonarState;
    
    /**
     * (�o�^����)<BR>
     * �o�^����<BR>
     */
    public Date registDate;
    
    /**
     * @@roseuid 418F39F7034B
     */
    public WEB3AccInfoStopInfo() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@�x�q�敪�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01125<BR>
     * �@@�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01126<BR>
     * <BR>
     * �Q�j�@@�Ǘ����b�N�敪�̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01127<BR>
     * �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01128<BR>
     * <BR>
     * �R�j�@@�Ǘ����b�N�����J�n���^�Ǘ����b�N�����I�����̃`�F�b�N <BR>
     * �@@�R�|�P�j�@@�ǂ��炩����݂̂̓��͂̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01129<BR>
     * �@@�@@�@@�@@�@@�@@�����������͂��A�������͂���Ă��Ȃ���΂Ȃ�Ȃ��B<BR>
     * �@@�R�|�Q�j�@@�Ǘ����b�N�����J�n�� > �Ǘ����b�N�����I�����j�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01130<BR>  
     * <BR>
     * �S�j�@@�x�X���b�N�敪�̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR> 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01131<BR>
     * �@@�S�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01132<BR>
     * <BR>
     * �T�j�@@�����F�敪�̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01133<BR>    
     * �@@�T�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01134<BR>
     * �@@�T�|�R�j�@@�i�����F�敪 == 0�F�F�j�̏ꍇ�A<BR>
     * �@@�@@�i�x�X���b�N�敪 == 0�FDEFAULT�i���b�N�łȂ��j && <BR>
     * �@@�@@ �Ǘ����b�N�敪 == 0�FDEFAULT�i���b�N�łȂ��j �j�ł���΁A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01135<BR>    
     * <BR>
     * �@@�@@�������F������ꍇ�́A�x�X���b�N�敪�C<BR>
     * �܂��͊Ǘ����b�N�敪�̂ǂ��炩�����b�N���ɂȂ��Ă��邱�ƁB<BR>
     * <BR>
     * �U�j�@@��~�󋵓o�^���R�̃`�F�b�N<BR>
     * �@@����~�󋵓o�^���R�ɓ��͂�����ꍇ�̂݃`�F�b�N����B<BR>
     * �@@�U�|�P�j�@@��������40byte���傫�������ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02225<BR>   
     * �@@�U�|�Q�j�@@�S�p�����ȊO���܂܂�Ă���ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02226<BR>   
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CF012023F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        /*
         * �P�j�@@�x�q�敪�̃`�F�b�N<BR>
         * �@@�P�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01125<BR>
         */
        if(yellowAccountDiv == null || "".equals(yellowAccountDiv))
        {
            log.debug("[�x�q�敪] = " + yellowAccountDiv);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01125, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�x�q�敪������");
        }
        /*
         * �@@�P�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01126<BR>          
         */
         if(!(WEB3YellowAccountDivDef.DEFAULT).equals(yellowAccountDiv) && 
            !(WEB3YellowAccountDivDef.YELLOW_ACCOUNT).equals(yellowAccountDiv))
         {
             log.debug("[�x�q�敪] = " + yellowAccountDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01126, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�x�q�敪�s���ȃR�[�h�l");
         }
         
        /* 
         * �Q�j�@@�Ǘ����b�N�敪�̃`�F�b�N<BR>
         * �@@�Q�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01127<BR>
         */
         if(mngLockDiv == null || "".equals(mngLockDiv))
         {
             log.debug("[�Ǘ����b�N�敪] = " + mngLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01127, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ǘ����b�N�敪������");
         }
         
        /* 
         * �@@�Q�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01128<BR>
         */
         if(!(WEB3LockDivDef.DEFAULT).equals(mngLockDiv) && !(WEB3LockDivDef.LOCKING).equals(mngLockDiv))
         {
             log.debug("[�Ǘ����b�N�敪] = " + mngLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01128, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ǘ����b�N�敪�s���ȃR�[�h�l");
         }
        /* 
         * �R�j�@@�Ǘ����b�N�����J�n���^�Ǘ����b�N�����I�����̃`�F�b�N <BR>
         * �@@�R�|�P�j�@@�ǂ��炩����݂̂̓��͂̏ꍇ�A��O���X���[����B<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01129<BR>
         * �@@�@@�@@�@@�@@�@@�����������͂��A�������͂���Ă��Ȃ���΂Ȃ�Ȃ��B<BR>
         */
         if((mngLockCancelStartDate == null && mngLockCancelEndDate != null) || 
            (mngLockCancelStartDate != null) && (mngLockCancelEndDate == null))
         {
             log.debug("[�Ǘ����b�N�����J�n��] = " + mngLockCancelStartDate);
             log.debug("[�Ǘ����b�N�����I����] = " + mngLockCancelEndDate);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01129, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ǘ����b�N�����J�n���^�Ǘ����b�N�����I�����ǂ��炩����݂̂̓���");
         }
         
        /* 
         * �@@�R�|�Q�j�@@�Ǘ����b�N�����J�n�� > �Ǘ����b�N�����I�����j�̏ꍇ�A<BR>
         * ��O���X���[����B<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01130<BR>  
         * <BR>
         */
         if(WEB3DateUtility.compareToDay(mngLockCancelStartDate, mngLockCancelEndDate) > 0)
         {
             log.debug("[�Ǘ����b�N�����J�n��] = " + mngLockCancelStartDate);
             log.debug("[�Ǘ����b�N�����I����] = " + mngLockCancelEndDate);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01130, 
                this.getClass().getName() + STR_METHOD_NAME,
				"�Ǘ����b�N�����J�n�� > �Ǘ����b�N�����I����");
         }
         
        /* 
         * �S�j�@@�x�X���b�N�敪�̃`�F�b�N<BR>
         * �@@�S�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR> 
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01131<BR>
         */
         if(branchLockDiv == null || "".equals(branchLockDiv))         
         {
             log.debug("[�x�X���b�N�敪] = " + branchLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01131, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�x�X���b�N�敪������");
         }
         
        /* 
         * �@@�S�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01132<BR>
         * <BR>
         */
         if(!(WEB3LockDivDef.DEFAULT).equals(branchLockDiv) && !(WEB3LockDivDef.LOCKING).equals(branchLockDiv))
         {
             log.debug("[�x�X���b�N�敪] = " + branchLockDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01132, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�x�X���b�N�敪�s���ȃR�[�h�l");
         }
         
        /* 
         * �T�j�@@�����F�敪�̃`�F�b�N<BR>
         * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01133<BR>
         */
         if(orderPermitDiv == null || "".equals(orderPermitDiv))
         {
             log.debug("[�����F�敪] = " + orderPermitDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01133, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�����F�敪������");
         }
         
        /*     
         * �@@�T�|�Q�j�@@�s���ȃR�[�h�l�̏ꍇ�A��O���X���[����B <BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01134<BR>
         */
         if(!(WEB3OrderPermitDivDef.AUTHORIZATION).equals(orderPermitDiv) && 
            !(WEB3OrderPermitDivDef.NOT_AUTHORIZATION).equals(orderPermitDiv))
         {
             log.debug("[�����F�敪] = " + orderPermitDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01134, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�����F�敪�s���ȃR�[�h�l");
         }
         
        /* 
         * �@@�T�|�R�j�@@�i�����F�敪 == 0�F�F�j�̏ꍇ�A<BR>
         * �@@�@@�i�x�X���b�N�敪 == 0�FDEFAULT�i���b�N�łȂ��j && <BR>
         * �@@�@@ �Ǘ����b�N�敪 == 0�FDEFAULT�i���b�N�łȂ��j �j�ł���΁A<BR>
         * ��O���X���[����B<BR>
         *         class: WEB3BusinessLayerException<BR>
         *         tag:   BUSINESS_ERROR_01135<BR>    
         * <BR>
         * �@@�@@�������F������ꍇ�́A�x�X���b�N�敪�C<BR>
         * �܂��͊Ǘ����b�N�敪�̂ǂ��炩�����b�N���ɂȂ��Ă��邱�ƁB<BR>
         */
         if((WEB3OrderPermitDivDef.AUTHORIZATION).equals(orderPermitDiv) &&
            ((WEB3LockDivDef.DEFAULT).equals(branchLockDiv) &&
                (WEB3LockDivDef.DEFAULT).equals(mngLockDiv)))
         {
             log.debug("[�����F�敪] = " + orderPermitDiv);
             log.debug("[�x�X���b�N�敪] = " + orderPermitDiv);
             log.debug("[�Ǘ����b�N�敪] = " + orderPermitDiv);
             throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01135, 
                this.getClass().getName() + STR_METHOD_NAME,
                "�����F�敪 == 0�F�F�j�̏ꍇ�i�x�X���b�N�敪 == 0�FDEFAULT�i���b�N�łȂ��j &&�Ǘ����b�N�敪 == 0�FDEFAULT�i���b�N�łȂ��j �j");
         }
         
         /*
          * �U�j�@@��~�󋵓o�^���R�̃`�F�b�N
          * �@@����~�󋵓o�^���R�ɓ��͂�����ꍇ�̂݃`�F�b�N����
          */
         if (!WEB3StringTypeUtility.isEmpty(stopStateRegistReason))
         {
             //�U�|�P�j�@@��������40byte���傫�������ꍇ�A��O���X���[����B
             int l_intByteLength = WEB3StringTypeUtility.getByteLength(stopStateRegistReason);
             if (l_intByteLength > 40)
             {
                 log.debug("[��~�󋵓o�^���R] = " + stopStateRegistReason);
                 throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02225, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "��~�󋵓o�^���R�̕�������40byte���傫�������ł��B");
             }
             //�U�|�Q�j�@@�S�p�����ȊO���܂܂�Ă���ꍇ�A��O���X���[����B
             if (!WEB3StringTypeUtility.isMulti(stopStateRegistReason))
             {
                 log.debug("[��~�󋵓o�^���R] = " + stopStateRegistReason);
                 throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02226, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "��~�󋵓o�^���R���S�p�����ȊO���܂܂�Ă���ł��B");
             }
         }
    }
}
@
