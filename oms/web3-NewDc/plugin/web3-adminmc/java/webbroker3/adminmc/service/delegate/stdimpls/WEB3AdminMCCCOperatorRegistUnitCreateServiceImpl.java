head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : CC�I�y���[�^�o�^���쐬�T�[�r�XImpl(WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23 ���z (���u) �V�K�쐬 
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginDao;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistUnitCreateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.util.WEB3LogUtility;

/**
 * (CC�I�y���[�^�o�^���쐬�T�[�r�XImpl)<BR>
 * CC�I�y���[�^�o�^���쐬�T�[�r�X�����N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl implements WEB3AdminMCCCOperatorRegistUnitCreateService 
{
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl.class);
    
    /**
     * (createCC�I�y���[�^�o�^���)<BR>
     * ���҃I�u�W�F�N�g���ACC�I�y���[�^�o�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * CC�I�y���[�^�o�^���𐶐�����B<BR>
     * <BR>
     * ���҃I�u�W�F�N�g.getDataSourceObject()�ɂĎ擾�������ҍs�I�u�W�F�N�g���<BR>
     * ���������I�u�W�F�N�g�ɁA�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���ԋp����B<BR>
     * <BR>
     * �@@���X�R�[�h = ���Ҏҍs.���X�R�[�h<BR>
     * �@@�I�y���[�^�R�[�h = ���ҍs.���҃R�[�h<BR>
     * �@@�I�y���[�^�� = ���ҍs.���ҕc�� �����Җ��i�����j�Ƃ��Ďg�p<BR>
     * �@@��s�����\�敪 = ���ҍs.��s�����ۃt���O<BR>
     * �@@�����R�[�h = ���ҍs.�����R�[�h<BR>
     * �@@�G���[�� = ���O�C���s��.get(���O�C������)<BR>
     * �@@�X�V���� = ���ҍs.�X�V����<BR>
     * �@@�X�V�҃R�[�h = ���ҍs.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�� ���O�C���s<BR>
     * �@@�ȉ��̏����Ń��O�C���e�[�u�����������A�擾�����s�I�u�W�F�N�g�B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@���O�C���e�[�u��.���O�C��ID = ����.getLoginId()<BR>
     * <BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit
     * @@roseuid 417F713B00CF
     */
    public WEB3AdminMCCCOperatorRegistUnit createCCOperatorRegistUnit(WEB3GentradeTrader l_trader) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createCCOperatorRegistUnit(WEB3GentradeTrader l_trader)";
        log.entering(l_strMethodName);
        
        //CC�I�y���[�^�o�^���𐶐�����
        WEB3AdminMCCCOperatorRegistUnit l_web3AdminMCCCOperatorRegistUnit =
            new WEB3AdminMCCCOperatorRegistUnit();
            
        //���҃I�u�W�F�N�g.getDataSourceObject()�ɂĎ擾�������ҍs�I�u�W�F�N�g���
        //���������I�u�W�F�N�g�ɁA�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���ԋp����B
        TraderRow l_traderRow = (TraderRow)l_trader.getDataSourceObject();
        
        //a> ���X�R�[�h = ���Ҏҍs.���X�R�[�h
        l_web3AdminMCCCOperatorRegistUnit.branchCode = l_traderRow.getBranchCode();
        
        //b> �I�y���[�^�R�[�h = ���ҍs.���҃R�[�h
        l_web3AdminMCCCOperatorRegistUnit.operatorCode = l_traderRow.getTraderCode();
        
        //c> �I�y���[�^�� = ���ҍs.���ҕc�� �����Җ��i�����j�Ƃ��Ďg�p
        l_web3AdminMCCCOperatorRegistUnit.operatorName = l_traderRow.getFamilyName();
        
        //d> ��s�����\�敪 = ���ҍs.��s�����ۃt���O
        l_web3AdminMCCCOperatorRegistUnit.accountOrderDiv = l_traderRow.getAccountOrderFlag();
        
        //e> �����R�[�h = ���ҍs.�����R�[�h
        l_web3AdminMCCCOperatorRegistUnit.departmentCode = l_traderRow.getDepartmentCode();
        
        //f> �G���[�� = ���O�C���s��.get(���O�C������)
        //(1)LoginId
        long l_lngLoginId = l_traderRow.getLoginId();
        
        LoginRow l_loginRow;
        try
        {
            //(2)LoginRow
            l_loginRow = LoginDao.findRowByPk(l_lngLoginId);         
        }  
        catch (DataFindException l_ex)
        {
            log.debug("__DataFindException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);                   
        }
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }   
        //(3)
        int l_intFailureCount = l_loginRow.getFailureCount();
        String l_strFailureCount;
        if (l_intFailureCount == 0)
        {
            l_strFailureCount = null;
        }
        else
        {
            l_strFailureCount = String.valueOf(l_intFailureCount);
        }
        
        l_web3AdminMCCCOperatorRegistUnit.errorCount = l_strFailureCount;         
        
        //g> �X�V���� = ���ҍs.�X�V����
        l_web3AdminMCCCOperatorRegistUnit.updateTimeStamp = l_traderRow.getLastUpdatedTimestamp();
        
        //h> �X�V�҃R�[�h = ���ҍs.�X�V�҃R�[�h
        l_web3AdminMCCCOperatorRegistUnit.updaterCode = l_traderRow.getLastUpdater();
        
        log.exiting(l_strMethodName);
        return l_web3AdminMCCCOperatorRegistUnit;
    }
    
    /**
     * (createCC�I�y���[�^�o�^���)<BR>
     * ���҃I�u�W�F�N�g�̔z����ACC�I�y���[�^�o�^��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g�̔z����쐬����B<BR>
     * <BR>
     * �����̈���[]�̊e�v�f���ɁAthis.createCC�I�y���[�^�o�^���(����)���R�[������CC�I�y���[�^�o�^�����쐬����B<BR>
     * �߂�l��z��ɂĕԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_trader - (����)<BR>
     * ���҃I�u�W�F�N�g�̔z��<BR>
     * 
     * @@return webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit[]
     * @@roseuid 417F713B00EE
     */
    public WEB3AdminMCCCOperatorRegistUnit[] createCCOperatorRegistUnit(WEB3GentradeTrader[] l_trader) 
        throws WEB3BaseException
    {
        String l_strMethodName = "createCCOperatorRegistUnit(WEB3GentradeTrader[] l_trader)";
        log.entering(l_strMethodName);
        
        //�����̈���[]�̊e�v�f���ɁAthis.createCC�I�y���[�^�o�^���(����)���R�[������CC�I�y���[�^�o�^�����쐬����B
        //�߂�l��z��ɂĕԋp����B
        
        //WEB3AdminMCCCOperatorRegistUnit[]�𐶐�����
        WEB3AdminMCCCOperatorRegistUnit[] l_web3AdminMCCCOperatorRegistUnits = 
            new WEB3AdminMCCCOperatorRegistUnit[l_trader.length];
            
        for (int i = 0; i < l_trader.length; i++)
        {
            l_web3AdminMCCCOperatorRegistUnits[i] = this.createCCOperatorRegistUnit(l_trader[i]);    
        }
        
        log.exiting(l_strMethodName);   
        return l_web3AdminMCCCOperatorRegistUnits;
    }
}
@
