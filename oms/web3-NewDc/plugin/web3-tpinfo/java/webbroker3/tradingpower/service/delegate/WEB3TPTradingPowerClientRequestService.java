head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�̓N���C�A���g���N�G�X�g�T�[�r�X�N���X(WEB3TPTradingPowerClientRequestService.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/12/22 asano(SCS) �V�K�쐬
 */
package webbroker3.tradingpower.service.delegate;

import java.text.NumberFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�]�̓N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * �]�̓N���C�A���g���N�G�X�g�T�[�r�X�B<BR>
 * 
 * @@author asano(SCS)
 */
public abstract class WEB3TPTradingPowerClientRequestService 
{
   /**
    * ���O���[�e�B���e�B
    */
   private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerClientRequestService.class);
   
   /**
    * (�R���X�g���N�^)
    * @@roseuid 41B667C2005D
    */
   public WEB3TPTradingPowerClientRequestService() 
   {
    
   }
   
   /**
    * (get�⏕����)<BR>
    * �igetSubAccount�̃I�[�o�[���[�h�j<BR>
    * 
    * �P�j���O�C���Z�L�����e�B�T�[�r�X���⏕�������擾����B<BR>
    * <BR>
    * �Q�j�ڋq�I�u�W�F�N�g���擾����B<BR>
    * <BR>
    * �R�jis�M�p�����J�݂̔���<BR>
    * <BR>
    * �@@���J�݂̏ꍇ<BR>
    * �@@�@@�⏕����<�����������(�a���)>�I�u�W�F�N�g���擾���A���^�[������B<BR>
    * �@@�����J�݂̏ꍇ<BR>
    * �@@�@@�⏕����<�����M�p�������(�ۏ؋�)>�I�u�W�F�N�g���擾���A���^�[������B<BR>
     * <BR>
    * @@return webbroker3.gentrade.WEB3GentradeSubAccount
    * @@roseuid 41B6603102DD
    */
   public WEB3GentradeSubAccount getSubAccount()
   {
       // OpLoginSecurityService����AccountId���擾
       OpLoginSecurityService l_loginService =
           (OpLoginSecurityService) Services.getService(
               OpLoginSecurityService.class);
        long l_lngAccountId = l_loginService.getAccountId();
        
        try
        {
            //�ڋq
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount( l_lngAccountId );
            //�⏕����
            SubAccount l_subAccount = null;
              
           //�M�p�����J�݂̔���
           if( !l_mainAccount.isMarginAccountEstablished( WEB3GentradeRepaymentDivDef.DEFAULT ) )
           {
                //�����ڋq�@@�����^�C�v�F�����������(�a���)
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
           }
           else
           {
                //�M�p�ڋq�@@�����^�C�v�F�����M�p�������(�ۏ؋�)>
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
          }
          
          //�⏕������ԋp
          return new WEB3GentradeSubAccount( (SubAccountRow)l_subAccount.getDataSourceObject() );
          
       } 
       catch (NotFoundException nfe)
       {
           log.error(nfe.getMessage(), nfe);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               getClass().getName() + "getSubAccount()",
               nfe);
       } 
       catch (DataQueryException dqe) {
           log.error(dqe.getMessage(), dqe);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               getClass().getName() + "getSubAccount()",
               dqe);
       } 
       catch (DataNetworkException dne) {
           log.error(dne.getMessage(), dne);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               getClass().getName() + "getSubAccount()",
               dne);
       }

    }
        
    /**
     * double�^��String�`���Ƀt�H�[�}�b�g����B<BR>
     * <BR>
     * @@param param
     * @@return String
     */
    protected String format(double l_param)
    {
        NumberFormat l_nf = NumberFormat.getInstance();
        l_nf.setGroupingUsed( false );
        return l_nf.format( l_param ); 
    }
    
}
@
