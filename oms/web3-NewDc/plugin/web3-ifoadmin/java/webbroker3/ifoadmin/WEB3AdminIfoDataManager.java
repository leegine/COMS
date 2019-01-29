head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�敨OP�f�[�^�}�l�[�W��(WEB3AdminIfoDataManager.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30�@@�Ӑ�(���u) �V�K�쐬
Revision History : 2007/07/17�@@�����F(���u) ���f��003
Revision History : 2009/03/03�@@�И���(���u) ���f��007,008
Revision History : 2009/03/09�@@����(���u) ���f��013
*/
package webbroker3.ifoadmin;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProductManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3ProcessIdDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.IfoDepositRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.data.HostFotypeOrderAllRow;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifoadmin.message.WEB3AdminIfoDepShortageReferenceRequest;
import webbroker3.ifodeposit.WEB3IfoDepositPersistentDataManager;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�Ǘ��ҁE�敨OP�f�[�^�}�l�[�W���j<BR>
 * <BR>
 * �Ǘ��ҁE�敨OP�f�[�^�}�l�[�W��<BR>
 * ���i�Ǘ�(����)�̃f�[�^�쐬�Ȃǂ��Ǘ�����N���X<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3AdminIfoDataManager<BR>
 * WEB3AdminIfoDataManager class.<BR>
 * <BR>
 * @@author �Ӑ�
 * @@version 1.0
 */
public class WEB3AdminIfoDataManager
{
    /** Log Variable.<BR> */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDataManager.class);

    /**
     * @@roseuid 41FDBE3C0109
     */
    public WEB3AdminIfoDataManager()
    {
    }

    /**
     * (get����)<BR>
     * �����̏����ɊY������敨OP�������擾����B<BR> 
     * ���p�����[�^.�敨�^�I�v�V�����敪 == null�̏ꍇ�A <BR>
�@@   * null��ԋp����B <BR>
�@@   * <BR>
     * �P�j�@@�p�����[�^.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ<BR> 
�@@   *   �敨OP�v���_�N�g�}�l�[�W��.get����()�ɏ������Ϗ�����B <BR>
     * <BR>
     *   [get����()�Ɏw�肷�����]<BR> 
�@@   *    �@@�،���ЁF�@@�p�����[�^.�،����<BR> 
�@@   *    �@@�����Y�����R�[�h�F�@@�p�����[�^.�w�����<BR> 
�@@   *    �@@�����F�@@�p�����[�^.����<BR> 
�@@   *    �@@�敨�I�v�V�������i�F�@@"�敨"�i�Œ�j<BR> 
�@@   *    �@@�s�g���i�F�@@0�i�Œ�j <BR>
�@@   *    �@@�����F�@@"DEFAULT"�i�Œ�j<BR> 
�@@   *    �@@�Ώێs��R�[�h�F�@@null�i�Œ�j <BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ<BR> 
     *   �敨OP�v���_�N�g�}�l�[�W��.get����()�ɏ������Ϗ�����B<BR> 
     * <BR>
     *   [get����()�Ɏw�肷�����]<BR> 
     *       �،���ЁF�@@�p�����[�^.�،����<BR> 
     *       �����Y�����R�[�h�F�@@�p�����[�^.�w�����<BR> 
     *       �����F�@@�p�����[�^.���� <BR>
     *       �敨�I�v�V�������i�F <BR>
     *       [�p�����[�^.�I�v�V�������i�敪 == "�v�b�g"�̏ꍇ]<BR> 
     *       "�v�b�g�I�v�V����" <BR>
     *       [��L�ȊO�̏ꍇ] <BR>
     *       "�R�[���I�v�V����" <BR>
     *       �s�g���i�F�@@�p�����[�^.�s�g���i<BR> 
     *       �����F�@@"DEFAULT"�i�Œ�j <BR>
     *       �Ώێs��R�[�h�F�@@null�i�Œ�j <BR>
     * <BR>
     * �R�j�@@get����()�̖߂�l��ԋp����B<BR> 
     *   ����L�����ɂė�O���X���[���ꂽ�ꍇ�Anull��ԋp����B<BR>
     * @@param l_institution �،����<BR>
     * @@param l_strFuturesOptionDivision �敨�^�I�v�V�����敪<BR>
     * @@param l_strTargetProductCode �w�����<BR>
     * @@param l_strDelivaryMonth ����<BR>
     * @@param l_strStrikePrice �s�g���i<BR>
     * @@param l_strOpProductType �I�v�V�������i�敪<BR>
     * @@return WEB3IfoProductImpl �敨OP����<BR>
     */
    public static WEB3IfoProductImpl getProduct(WEB3GentradeInstitution l_institution ,
        String l_strFuturesOptionDivision ,
        String l_strTargetProductCode ,
        String l_strDelivaryMonth ,
        String l_strStrikePrice ,
        String l_strOpProductType) 
    {
        final String STR_METHOD_NAME = "getProduct(WEB3GentradeInstitution, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IfoProductImpl l_ifoProductImpl = null;
        
        //���p�����[�^.�敨�^�I�v�V�����敪 == null�̏ꍇ�A 
        //�@@null��ԋp����B 
        if (l_strFuturesOptionDivision == null) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        try 
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.IFO);
            
            WEB3IfoProductManagerImpl  l_ifoProductManager = 
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
            
            //�P�j�@@�p�����[�^.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ 
            //�@@�敨OP�v���_�N�g�}�l�[�W��.get����()�ɏ������Ϗ�����B
            if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuturesOptionDivision)) 
            {
                l_ifoProductImpl = l_ifoProductManager.getIfoProduct(l_institution,
                    l_strTargetProductCode,
                    l_strDelivaryMonth,
                    IfoDerivativeTypeEnum.FUTURES,
                    0D,
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    null);
            }
            
            //�Q�j�@@�p�����[�^.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ 
            //�@@�敨OP�v���_�N�g�}�l�[�W��.get����()�ɏ������Ϗ�����B
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFuturesOptionDivision)) 
            {
                // �敨�I�v�V�������i�F 
                //  [�p�����[�^.�I�v�V�������i�敪 == "�v�b�g"�̏ꍇ] 
                //�@@"�v�b�g�I�v�V����" 
                //�@@[��L�ȊO�̏ꍇ] 
                //�@@"�R�[���I�v�V����" 
                IfoDerivativeTypeEnum l_ifoDerivativeTypeEnum = null;
                if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_strOpProductType)) 
                {
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.PUT_OPTIONS;
                } 
                else 
                {
                    l_ifoDerivativeTypeEnum = IfoDerivativeTypeEnum.CALL_OPTIONS;
                }
                
                l_ifoProductImpl = l_ifoProductManager.getIfoProduct(l_institution,
                    l_strTargetProductCode,
                    l_strDelivaryMonth,
                    l_ifoDerivativeTypeEnum,
                    Double.parseDouble(l_strStrikePrice),
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    null);
            }
        } 
        catch (NotFoundException l_ex) 
        {
            //�R�j�@@get����()�̖߂�l��ԋp����B 
            //�@@����L�����ɂė�O���X���[���ꂽ�ꍇ�Anull��ԋp����B
            log.debug(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (WEB3BaseException l_ex) 
        {
            //�R�j�@@get����()�̖߂�l��ԋp����B 
            //�@@����L�����ɂė�O���X���[���ꂽ�ꍇ�Anull��ԋp����B
            log.debug(STR_METHOD_NAME,l_ex);
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ifoProductImpl;
    }

    /**
     * (get�ڋq�ꗗ)<BR>
     * �����̏����ɊY������ڋq�̈ꗗ��ԋp����B<BR> 
     * <BR>
     * �P�j ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j DB���� <BR>
     * �@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B <BR>
     * �@@�Q�|�P�j �g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@�@@[get�ڋq()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h <BR>
     * �@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h <BR>
     * �@@�@@�@@�����R�[�h�F�@@�p�����[�^.�ڋq�R�[�h <BR>
     * <BR>
     * �@@�Q�|�Q�j �@@�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A <BR>
     * �@@�@@�@@��������ArrayList�ɒǉ�����B <BR>
     * �@@ <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B <BR>
     * �@@��toArray()�̖߂�l.length��0�̏ꍇ�A <BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * �@@class : WEB3BusinessLayerException<BR>
     * �@@tag : BUSINESS_ERROR_01037<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@return webbroker3.gentrade.WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901E5
     */
    public static WEB3GentradeMainAccount[] getAccountList(
        String l_strInstitutionCode, 
        String[] l_strBranchCodes, 
        String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getAccountList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCodes == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j ArrayList�𐶐�����B  
        List l_lisAccounts = new ArrayList();
        
        //�Q�j DB����  
        //�@@�p�����[�^.���X�R�[�h[]�̗v�f�����ȉ��̏������J��Ԃ��B  
        //�@@�Q�|�P�j �g���A�J�E���g�}�l�[�W��.get�ڋq()���\�b�h���R�[������B  
        //�@@�@@[get�ڋq()�ɃZ�b�g����p�����[�^]  
        //�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h  
        //�@@�@@�@@���X�R�[�h�F�@@�����Ώۂ̕��X�R�[�h  
        //�@@�@@�@@�����R�[�h�F�@@�p�����[�^.�ڋq�R�[�h  
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountMananger = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        for (int i = 0; i < l_strBranchCodes.length; i++) 
        {
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = 
                    l_accountMananger.getMainAccount(l_strInstitutionCode, l_strBranchCodes[i], l_strAccountCode);
            }
            catch (WEB3BaseException l_ex)
            {
                continue;
            }
            
            //�@@�Q�|�Q�j �@@�Q�|�P�j�̌��ʂ��擾�ł����ꍇ�́A��������ArrayList�ɒǉ�����B  
            if (l_mainAccount != null) 
            {
                l_lisAccounts.add(l_mainAccount);
            }
        }
        //�R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B 
        WEB3GentradeMainAccount[] l_mainAccounts = new WEB3GentradeMainAccount[l_lisAccounts.size()];
        l_lisAccounts.toArray(l_mainAccounts);
        
        //�@@��toArray()�̖߂�l.length��0�̏ꍇ�A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B
        if (l_mainAccounts.length == 0)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                "WEB3AdminIfoDataManager."+ STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }
    
    /**
     * (reset����J�����_�R���e�L�X�g)<BR>
     * ����J�����_�R���e�L�X�g�̒l�������̒l�ōăZ�b�g����B<BR>
     * <BR>
     * �P�j�@@����J�����_�R���e�L�X�g���擾����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[������B<BR>
     * <BR>
     * �@@[getAttribute()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * <BR>
     * �@@���擾�ł��Ȃ������ꍇ�́A����J�����_�R���e�L�X�g�𐶐����A<BR>
     * �@@�@@�@@�ȍ~�̏����Ŏg�p���邱�ƁB<BR>
     * <BR>
     * �Q�j�@@�擾��������J�����_�R���e�L�X�g�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@���Ή�����p�����[�^��null�̏ꍇ�́A�ăZ�b�g���s��Ȃ��B<BR>
     * �@@�@@ID�ɊY������I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A<BR>
     * �@@�@@�u�Y���f�[�^�Ȃ��v�̃V�X�e���G���[���X���[����B<BR>
     * �@@class�@@:�@@WEB3SystemLayerException<BR>
     * �@@tag�@@:�@@SYSTEM_ERROR_80005<BR>
     * <BR>
     * �@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@���X�R�[�h = ���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h <BR>
     * �@@�@@�s��R�[�h = "DEFAULT"<BR>
     * �@@�@@�����R�[�h = �p�����[�^.����ID�ɊY������敨OP����.�����Y�����R�[�h <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�������A�p�����[�^.����ID==null�̏ꍇ�́A"DEFAULT"���Z�b�g<BR>
     * �@@�@@��t���ԋ敪 = "�����w���敨OP"<BR>
     * <BR>
     * �R�j�@@����J�����_�R���e�L�X�g���ăZ�b�g����B<BR>
     * �@@ThreadLocalSystemAttributesRegistry.setAttribute()���R�[������B<BR>
     * <BR>
     * �@@[setAttribute()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH<BR>
     * �@@�@@arg1�F�@@�v���p�e�B�Z�b�g��������J�����_�R���e�L�X�g<BR>
     * <BR>
     * �S�j�@@��t�����A���t���[���̃��Z�b�g���s���B<BR>
     * �@@������ԊǗ�.setTimestamp()���R�[������B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_branchId - (���XID)<BR>
     * ���XID
     * @@param l_productId - (����ID)<BR>
     * ����ID
     * @@throws WEB3BaseException 
     * @@roseuid 4469593901F6
     */
    public static void resetTradingCalContext(
        String l_strInstitutionCode, 
        Long l_branchId, 
        Long l_productId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "resetTradingCalContext(String, Long, Long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����J�����_�R���e�L�X�g���擾����B 
        //�@@ThreadLocalSystemAttributesRegistry.getAttribute()���R�[������B 
        Object l_object = 
            ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //�@@���擾�ł��Ȃ������ꍇ�́A����J�����_�R���e�L�X�g�𐶐����A�ȍ~�̏����Ŏg�p���邱�ƁB 
        WEB3GentradeTradingClendarContext l_context = null;
        if (l_object != null)
        {
            l_context = (WEB3GentradeTradingClendarContext)l_object;
        }
        else
        {
            l_context = new WEB3GentradeTradingClendarContext();
        }
        
        //�Q�j�@@�擾��������J�����_�R���e�L�X�g�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        //�@@���Ή�����p�����[�^��null�̏ꍇ�́A�ăZ�b�g���s��Ȃ��B
        //�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
        if (l_strInstitutionCode != null)
        {
            l_context.setInstitutionCode(l_strInstitutionCode);
        }
        
        //�@@�@@���X�R�[�h = �p�����[�^.���XID�ɊY�����镔�X�R�[�h 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        if (l_branchId != null)
        {
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                Branch l_branch = l_accountManager.getBranch(l_branchId.longValue());
                l_context.setBranchCode(l_branch.getBranchCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        
        //�@@�@@�s��R�[�h = "DEFAULT"  
        l_context.setMarketCode(WEB3ProductCodeDef.DEFAULT);
        
        //�@@�@@�����R�[�h = �p�����[�^.����ID�ɊY������敨OP����.�����Y�����R�[�h 
        if (l_productId != null)
        {
            IfoProductManager l_ifoProductManager = 
                (IfoProductManager)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
            try
            {
                WEB3IfoProductImpl l_ifoProduct = (WEB3IfoProductImpl)l_ifoProductManager.getProduct(l_productId.longValue());
                l_context.setProductCode(l_ifoProduct.getUnderlyingProductCode());
            }
            catch (NotFoundException l_nfe)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    "�e�[�u���ɊY������f�[�^������܂���B");
            }
        }
        //�������A�p�����[�^.����ID==null�̏ꍇ�́A"DEFAULT"���Z�b�g
        else
        {
            l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        }
            
        //��t���ԋ敪 = "�����w���敨OP" 
        l_context.setTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

        //�R�j�@@����J�����_�R���e�L�X�g���ăZ�b�g����B 
        //�@@ThreadLocalSystemAttributesRegistry.setAttribute()���R�[������B 
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH, l_context);

        //�S�j�@@��t�����A���t���[���̃��Z�b�g���s���B 
        WEB3GentradeTradingTimeManagement.setTimestamp();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�蓮�����Ώے����P�ʈꗗ)<BR>
     * �蓮�����Ώۂ̒����P�ʂ̈ꗗ���擾����B<BR>
     * <BR>
     *�蓮�����Ώۂ̒����P�ʂ̈ꗗ���擾����B <BR>
     *<BR>
     *�P�j�@@��������������i�FString�j�A <BR>
     *�@@���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B <BR>
     *<BR>
     *�Q�j�����������쐬����B <BR>
     *�@@�Q�|�P�j�@@�ȉ��̒������������������ɒǉ�����B <BR>
     *�@@�@@[��������] <BR>
     *�@@�@@�@@�������� = "DEFAULT" <BR>
     *�@@�@@�@@And �����L����� = "�I�[�v��" <BR>
     *�@@�@@�@@And �s�ꂩ��m�F�ς݂̐��� = null <BR>
     *<BR>
     *�@@�@@�������������� = " and order_condition_type = ?" <BR>
     *�@@�@@�@@�@@�@@�@@�@@+ " and order_open_status = ?" <BR>
     *�@@�@@�@@�@@�@@�@@�@@+ " and confirmed_quantity is null" <BR>
     *<BR>
     *�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j <BR>
     *�@@�@@�@@�E"DEFAULT�i�����w��Ȃ��j" <BR>
     *�@@�@@�@@�E"�I�[�v��" <BR>
     *<BR>
     *�@@�Q�|�Q�j�@@���X���������������ɒǉ�����B <BR>
     *�@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B <BR>
     *<BR>
     *�@@�@@�������������� += " and branch_id in (?, ?,,,) " <BR>
     *�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�ɊY�����镔�X.���XID�� <BR>
     *�@@�@@�v�f�����A�ǉ�����B <BR>
     *<BR>
     *�@@�@@�����X���擾����ۂɁA�p�����[�^.�،���Ђ��g�p����B <BR>
     *�@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v <BR>
     *�@@�@@�@@�̋Ɩ��G���[���X���[����B <BR>
     *<BR>
     *�@@�Q�|�R�j�@@�p�����[�^.����ID != null�̏ꍇ�A <BR>
     *�@@�@@�������������������ɒǉ�����B <BR>
     *<BR>
     *�@@�@@�������������� += " and product_id = ? " <BR>
     *�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.����ID��ǉ�����B <BR>
     *<BR>
     *�@@�Q�|�S�j�@@�����������������������ɒǉ�����B <BR>
     *�@@�@@�Q�|�S�|�P�j�@@�p�����[�^.����敪�ꗗ�̗v�f����Loop���A"?"��ǉ�����B <BR>
     *<BR>
     *�@@�@@�@@�������������� += " and order_type in (?,?,,,) " <BR>
     *�@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.����敪�ꗗ�̑S�v�f��ǉ�����B <BR>
     *<BR>
     *�@@�Q�|�T�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A <BR>
     *�@@�@@�ڋq���������������ɒǉ�����B <BR>
     *�@@�@@�Q�|�T�|�P�j�@@�Ǘ��ҁE�敨OP�f�[�^�}�l�[�W��.get�ڋq�ꗗ()�� <BR>
     *�@@�@@�@@�R�[������B <BR>
     *<BR>
     *�@@�@@�@@[get�ڋq�ꗗ()�Ɏw�肷�����] <BR>
     *�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h <BR>
     *�@@�@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h <BR>
     *�@@�@@�@@�@@�ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h <BR>
     *<BR>
     *�@@�@@�Q�|�T�|�Q�j�@@�Q�|�T�|�P�j�̖߂�l�̗v�f�����A����������"?"���A <BR>
     *�@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌���ID��ǉ�����B <BR>
     *<BR>
     *�@@�@@�@@�������������� += " and account_id in (?, ?,,,) " <BR>
     *�@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j <BR>
     *�@@�@@�@@�@@�E�Q�|�T�|�P�j�̖߂�l�̊e�v�f.����ID <BR>
     *<BR>
     *�@@�Q�|�U�j�@@�p�����[�^.����IDFrom != null�@@���� <BR>
     *�@@�@@�p�����[�^.����IDTo != null�̏ꍇ�A����ID�����W������ <BR>
     *�@@�@@���������ɒǉ�����B <BR>
     *�@@�@@���񓯊��������s���ꍇ�ɐݒ肳���B <BR>
     *<BR>
     *�@@�@@�������������� += " and account_id >= ?" <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@+ " and account_id <= ?" <BR>
     *�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j <BR>
     *�@@�@@�@@�E�p�����[�^.����IDFrom <BR>
     *�@@�@@�@@�E�p�����[�^.����IDTo <BR>
     *<BR>
     *�R�j�@@DB����������B <BR>
     *�@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     *<BR>
     *�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     *�@@�@@�@@arg0�F�@@�敨OP�����P��Row.TYPE  <BR>
     *�@@�@@�@@arg1�F�@@�쐬������������������ <BR>
     *�@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l <BR>
     *<BR>
     *�@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     *<BR>
     *�S�j�@@�L���[�e�[�u������������������쐬����B <BR>
     *�@@�S�|�P�j�@@��{�̌�������������A�f�[�^�R���e�i���쐬����B <BR>
     *�@@�@@���f�[�^�R���e�i�́A�ȑO�̂��̂��g�p����ꍇ�A�K���v�f��S��clear���邱�ƁB <BR>
     *�@@�@@[��{����] <BR>
     *�@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h <BR>
     *�@@�@@�@@�����敪 = "������" <BR>
     *<BR>
     *�@@�@@�������������� = "institution_code = ?" <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@+ " and status = ?" <BR>
     *�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j <BR>
     *�@@�@@�@@�E�p�����[�^.�،���� <BR>
     *�@@�@@�@@�E"������" <BR>
     *<BR>
     *�@@�S�|�Q�j�@@���X���������������ɒǉ�����B <BR>
     *�@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B <BR>
     *<BR>
     *�@@�@@�������������� += " and branch_code in (?, ?,,,)" <BR>
     *�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�̑S�v�f��ǉ�����B <BR>
     *<BR>
     *�@@�S�|�R�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A <BR>
     *�@@�@@�ڋq��������������������ɒǉ�����B <BR>
     *<BR>
     *�@@�@@�S�|�R�|�P�j�@@�Ǘ��ҁE�敨OP�f�[�^�}�l�[�W��.get�ڋq�ꗗ()�� <BR>
     *�@@�@@�@@�R�[������B <BR>
     *<BR>
     *�@@�@@�@@[get�ڋq�ꗗ()�Ɏw�肷�����] <BR>
     *�@@�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h <BR>
     *�@@�@@�@@�@@���X�R�[�h�F�@@�p�����[�^.���X�R�[�h <BR>
     *�@@�@@�@@�@@�ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h <BR>
     *<BR>
     *�@@�@@�S�|�R�|�Q�j�@@�S�|�R�|�P�j�̖߂�l�̗v�f�����A����������"?"���A <BR>
     *�@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌����R�[�h��ǉ�����B <BR>
     *<BR>
     *�@@�@@�@@�������������� += " and account_code in (?, ?,,,) " <BR>
     *�@@�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j <BR>
     *�@@�@@�@@�@@�E�S�|�R�|�P�j�̖߂�l�̊e�v�f.�����R�[�h <BR>
     *<BR>
     *�T�j�@@���ʃR�[�h���X�g�i�FArrayList�j�𐶐�����B <BR>
     *<BR>
     *�U�j�@@�敨OP��������L���[�e�[�u������������B <BR>
     *�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     *<BR>
     *�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     *�@@�@@�@@arg0�F�@@�敨OP��������L���[Row.TYPE  <BR>
     *�@@�@@�@@arg1�F�@@�쐬������������������ <BR>
     *�@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l <BR>
     *<BR>
     *�@@�������ʂ��擾�ł����ꍇ�A�������ʂ̊e�v�f.���ʃR�[�h�� <BR>
     *�@@���ʃR�[�h���X�g�ɒǉ�����B <BR>
     *<BR>
     *�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     *<BR>
     *�V�j�@@�����Ώے������X�g�i�FArrayList�j�𐶐�����B <BR>
     *<BR>
     *�W�j�@@�����Ώے������m�肷��B <BR>
     *�@@�R�j�̖߂�l(=�����Ώۂ̐敨OP�����P��List)�̗v�f�����ALoop�������s���A <BR>
     *�@@�����Ώۂ̗v�f.���ʃR�[�h�����ʃR�[�h���X�g�Ɋ܂܂��ꍇ�A <BR>
     *�@@�����Ώے������X�g�ɏ����Ώۂ̗v�f��ǉ�����B <BR>
     *<BR>
     *�X�j�@@�����Ώے������X�g.toArray()�̖߂�l��ԋp����B <BR>
     *�@@�������Ώے������X�g.size() == 0�̏ꍇ�Anull��ԋp����B <BR>
     * @@param l_institution - (�،����)<BR>
     * �،����
     * @@param l_strBranchCodes - (���X�R�[�h)<BR>
     * ���X�R�[�h�̔z��
     * @@param l_strProductID - (����ID)<BR>
     * ����ID
     * @@param l_strTradingTypeList - (����敪�ꗗ)<BR>
     * ����敪�ꗗ
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     * @@param l_accountIdFrom - (����IDFrom)<BR>
     * ����IDFrom
     * @@param l_accountIdTo - (����IDTo)<BR>
     * ����IDTo
     * @@return IfoOrderUnitRow[]
     * @@throws WEB3BaseException 
     * @@roseuid 44695939020C
     */
    public static IfoOrderUnitRow[] getManualExpireOrderUnits(
        WEB3GentradeInstitution l_institution, 
        String[] l_strBranchCodes, 
        String l_strProductID, 
        String[] l_strTradingTypeList, 
        String l_strAccountCode, 
        Long l_accountIdFrom, 
        Long l_accountIdTo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getManualExpireOrderUnits(" 
            + "WEB3GentradeInstitution, String[], String, String[], String, Long, Long)";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@��������������i�FString�j�A  
        //�@@���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B 
        List l_lisQueryContainers = new ArrayList();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        //�Q�j�����������쐬����B 
        //�@@�Q�|�P�j�@@�ȉ��̒������������������ɒǉ�����B 
        //      [��������] 
        //     �@@�������� = "DEFAULT" 
        //     �@@And �����L����� = "�I�[�v��" 
        //     �@@And �s�ꂩ��m�F�ς݂̐��� = null 
        //     �������������� = " and order_condition_type = ?" 
        //     �@@+ " and order_open_status = ?" 
        //     �@@+ " and confirmed_quantity is null" 
        StringBuffer l_sbQueryString = new StringBuffer();
        l_sbQueryString.append("order_condition_type = ?");
        l_sbQueryString.append(" and order_open_status = ?");
        l_sbQueryString.append(" and confirmed_quantity is null");
        
        //�@@�@@�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B)
        //�@@�@@�@@�E"DEFAULT�i�����w��Ȃ��j" 
        l_lisQueryContainers.add(WEB3OrderingConditionDef.DEFAULT);
        
        //�@@�@@�@@�E"�I�[�v��" 
        l_lisQueryContainers.add(OrderOpenStatusEnum.OPEN);
        
        //�@@�Q�|�Q�j�@@���X���������������ɒǉ�����B  
        //�@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
            // �������������� += " and branch_id in (?, ?,,,) " 
            StringBuffer l_sbBranchIdForQuery = new StringBuffer();
            l_sbBranchIdForQuery.append(" and branch_id in (");
            try 
            {
                for (int i = 0; i < l_strBranchCodes.length; i++) 
                {
                    l_sbBranchIdForQuery.append("?,");
                    
                    //�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�ɊY�����镔�X.���XID��v�f�����Aadd����B
                    Branch l_branch = l_accountManager.getBranch(l_institution, l_strBranchCodes[i]);
                    l_lisQueryContainers.add(new Long(l_branch.getBranchId()));
                }
            }
            catch (NotFoundException l_ex)
            {
                //�@@�@@�����X���擾����ۂɁA�p�����[�^.�،���Ђ��g�p����B 
                //�@@�@@���擾���ɗ�O�ƂȂ����ꍇ�A�u�����ɊY������f�[�^�����݂��Ȃ��B�v�̋Ɩ��G���[���X���[����B 
                log.error("�����ɊY������f�[�^�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    "�����ɊY������f�[�^�����݂��Ȃ��B");
            }
            
            l_sbQueryString.append(l_sbBranchIdForQuery.substring(0, l_sbBranchIdForQuery.length() - 1));
            l_sbQueryString.append(")");
        }

        //�@@�Q�|�R�j�@@�p�����[�^.����ID != null�̏ꍇ�A 
        //�@@�@@�������������������ɒǉ�����B 
        if (l_strProductID != null) 
        {
            // �������������� += " and product_id = ? " 
            l_sbQueryString.append(" and product_id = ?");
            
            //�f�[�^�R���e�i�Ƀp�����[�^.����ID��ǉ�����B  
            l_lisQueryContainers.add(String.valueOf(l_strProductID));
        }
        
        //�@@�Q�|4�j�@@�����������������������ɒǉ�����B 
        //�@@�@@�Q�|4�|�P�j�@@�p�����[�^.����敪�ꗗ�̗v�f����Loop���A"?"��ǉ�����B  
        //�@@�@@�@@�������������� += " and order_type in (?,?,,,) "  
        //�@@�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.����敪�ꗗ�̑S�v�f��ǉ�����B  �@@�@@
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0) 
        {
            StringBuffer l_sbIfoForQuery = new StringBuffer();
            l_sbIfoForQuery.append(" and order_type in (");
            for (int i = 0; i < l_strTradingTypeList.length; i++) 
            {
                l_sbIfoForQuery.append("?,");
                l_lisQueryContainers.add(l_strTradingTypeList[i]);
            }
            l_sbQueryString.append(l_sbIfoForQuery.substring(0, l_sbIfoForQuery.length() -1));
            l_sbQueryString.append(")");
        }
        
        //�@@�Q�|5�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A
        //�@@�@@�ڋq���������������ɒǉ�����B 
        if (l_strAccountCode != null)
        {
            //�Q�|�T�|�P�j�@@�Ǘ��ҁE�敨OP�f�[�^�}�l�[�W��.get�ڋq�ꗗ()�� 
            //�@@�R�[������B 
            //�@@[get�ڋq�ꗗ()�Ɏw�肷�����] 
            // �،���ЃR�[�h�F�@@�p�����[�^.�،����.�،���ЃR�[�h 
            // ���X�R�[�h�F�@@�p�����[�^.���X�R�[�h 
            // �ڋq�R�[�h�F�@@�p�����[�^.�ڋq�R�[�h 
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminIfoDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //�@@�@@�Q�|�T�|�Q�j�@@�Q�|�T�|�P�j�̖߂�l�̗v�f�����A����������"?"���A 
            //�@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌���ID��ǉ�����B�@@�@@�@@
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //�@@�������������� += " and account_id in (?, ?,,,) " 
                StringBuffer l_sbAccountIdForQuery = new StringBuffer();
                l_sbAccountIdForQuery.append(" and account_id in (");
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountIdForQuery.append("?,");
                    
                    // �f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j 
                    //�@@�E�Q�|�T�|�P�j�̖߂�l�̊e�v�f.����ID 
                    l_lisQueryContainers.add(String.valueOf(l_mainAccounts[i].getAccountId()));
                }
                
                l_sbQueryString.append(l_sbAccountIdForQuery.substring(0, l_sbAccountIdForQuery.length() - 1));
                l_sbQueryString.append(")");
            }
        }
        
        //�@@�Q�|6�j�@@�p�����[�^.����IDFrom != null�@@���� 
        //�@@�@@�p�����[�^.����IDTo != null�̏ꍇ�A����ID�����W������ 
        //�@@�@@���������ɒǉ�����B 
        //�@@�@@���񓯊��������s���ꍇ�ɐݒ肳���B �@@�@@
        if (l_accountIdFrom != null && l_accountIdTo != null) 
        {
            //�@@�@@�������������� += " and account_id >= ?" + " and account_id <= ?" 
            l_sbQueryString.append(" and account_id >= ?");
            l_sbQueryString.append(" and account_id <= ?");
            
            //�E�p�����[�^.����IDFrom 
            l_lisQueryContainers.add(l_accountIdFrom);
            
            //�E�p�����[�^.����IDTo 
            l_lisQueryContainers.add(l_accountIdTo);
        }
        
        //�R�j�@@DB����������B 
        //�@@�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
        QueryProcessor l_queryProcessor = null;
        List l_lisRows = null;
        Object[] l_queryContainers = new Object[l_lisQueryContainers.size()];
        l_lisQueryContainers.toArray(l_queryContainers);
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            
            //�@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
            //�@@�@@�@@arg0�F�@@�敨OP�����P��Row.TYPE  
            //�@@�@@�@@arg1�F�@@�쐬������������������ 
            //�@@�@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l 
            l_lisRows = l_queryProcessor.doFindAllQuery(
                    IfoOrderUnitRow.TYPE,
                    l_sbQueryString.toString(),
                    l_queryContainers);
            
            // ���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
            if (l_lisRows == null || l_lisRows.isEmpty())
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        catch (DataQueryException l_dqex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dqex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_dqex.getMessage(),
                l_dqex);
        }
        catch (DataNetworkException l_dnex)
        {
            log.error("DB�A�N�Z�X�G���[", l_dnex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_dnex.getMessage(),
                l_dnex);
        }
         
        //�S�j�@@�L���[�e�[�u������������������쐬����B 
        //�@@�S�|�P�j�@@��{�̌�������������A�f�[�^�R���e�i���쐬����B 
        //�@@�@@���f�[�^�R���e�i�́A�ȑO�̂��̂��g�p����ꍇ�A�K���v�f��S��clear���邱�ƁB 
        //�@@�@@[��{����] 
        //�@@�@@�@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h 
        //�@@�@@�@@�����敪 = "������" 
        StringBuffer l_sbQuery = new StringBuffer();
        List l_lisIfoQueryContainers = new ArrayList();
        
        //�@@�@@�������������� = "institution_code = ?" + " and status = ?" 
        l_sbQuery.append("institution_code = ?");
        l_sbQuery.append(" and status = ?");
        
        //�E�p�����[�^.�،���� 
        l_lisIfoQueryContainers.add(l_institution.getInstitutionCode());

        //�E"������" 
        l_lisIfoQueryContainers.add(WEB3FrontOrderStatusDef.NOT_DEAL);
        
        //�@@�S�|�Q�j�@@���X���������������ɒǉ�����B 
        //�@@�@@�p�����[�^.���X�R�[�h�̗v�f����"?"��ǉ�����B 
        if (l_strBranchCodes != null && l_strBranchCodes.length != 0) 
        {
            //�@@�@@�������������� += " and branch_code in (?, ?,,,)" 
            //�@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���X�R�[�h�̑S�v�f��ǉ�����B
            StringBuffer l_sbBranchCodeForQuery = new StringBuffer();
            l_sbBranchCodeForQuery.append(" and branch_code in (");
            for (int i = 0; i < l_strBranchCodes.length; i++) 
            {
                l_sbBranchCodeForQuery.append("?,");
                l_lisIfoQueryContainers.add(l_strBranchCodes[i]);
            }
            
            l_sbQuery.append(l_sbBranchCodeForQuery.substring(0, l_sbBranchCodeForQuery.length() - 1));
            l_sbQuery.append(")");
        }
        
        //�@@�S�|�R�j�@@�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A 
        //�@@�@@�ڋq��������������������ɒǉ�����B 
        //�@@�@@�S�|3�|�P�j�@@�Ǘ��ҁE�敨OP�f�[�^�}�l�[�W��.get�ڋq�ꗗ()�� 
        //�@@�@@�@@�R�[������B 
        if (l_strAccountCode != null) 
        {
            WEB3GentradeMainAccount[] l_mainAccounts = 
                WEB3AdminIfoDataManager.getAccountList(
                    l_institution.getInstitutionCode(), 
                    l_strBranchCodes, 
                    l_strAccountCode);
            
            //�@@�@@�S�|3�|�Q�j�@@�S�|3�|�P�j�̖߂�l�̗v�f�����A����������"?"���A 
            //�@@�@@�@@�f�[�^�R���e�i�ɁA�e�v�f�̌����R�[�h��ǉ�����B 
            if (l_mainAccounts != null && l_mainAccounts.length != 0) 
            {
                //�������������� += " and account_code in (?, ?,,,) "
                //�f�[�^�R���e�i�i�ȉ��̏��Œǉ�����B�j 
                //�@@�@@�@@�@@�E�S�|3�|�P�j�̖߂�l�̊e�v�f.�����R�[�h 
                StringBuffer l_sbAccountsForQuery = new StringBuffer();
                l_sbAccountsForQuery.append(" and account_code in (");
                
                for (int i = 0; i < l_mainAccounts.length; i++) 
                {
                    l_sbAccountsForQuery.append("?,");
                    l_lisIfoQueryContainers.add(l_mainAccounts[i].getAccountCode());
                }
                
                l_sbQuery.append(l_sbAccountsForQuery.substring(0, l_sbAccountsForQuery.length() - 1));
                l_sbQuery.append(")");
            }
        }
        
        //�T�j�@@���ʃR�[�h���X�g�i�FArrayList�j�𐶐�����B 
        List l_lisOrderRequestNumbers = new ArrayList();
        
        //�U�j�@@�敨OP��������L���[�e�[�u������������B  
        if (l_strTradingTypeList != null && l_strTradingTypeList.length != 0)
        {
            //�@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
            QueryProcessor l_ifoEqueryProcessor =null;
            List l_lisIfoRows = null;
            Object[] l_ifoQueryContainers = new Object[l_lisIfoQueryContainers.size()];
            l_lisIfoQueryContainers.toArray(l_ifoQueryContainers);
            try
            {
                l_ifoEqueryProcessor = Processors.getDefaultProcessor();
                l_lisIfoRows = l_ifoEqueryProcessor.doFindAllQuery(
                    HostFotypeOrderAllRow.TYPE,
                    l_sbQuery.toString(),
                    l_ifoQueryContainers);
            }
            catch (DataQueryException l_dqex)
            {
                log.error("DB�A�N�Z�X�G���[", l_dqex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    l_dqex.getMessage(),
                    l_dqex);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error("DB�A�N�Z�X�G���[", l_dnex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                    l_dnex.getMessage(),
                    l_dnex);
            }
            if (!l_lisIfoRows.isEmpty())
            {
                for (int i = 0; i < l_lisIfoRows.size(); i++) 
                {
                    //�@@�������ʂ��擾�ł����ꍇ�A�������ʂ̊e�v�f.���ʃR�[�h�� 
                    //�@@���ʃR�[�h���X�g�ɒǉ�����B 
                    HostFotypeOrderAllRow l_row = (HostFotypeOrderAllRow)l_lisIfoRows.get(i);
                    l_lisOrderRequestNumbers.add(l_row.getOrderRequestNumber());
                }
            } 
            else 
            {
                // �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return null;
            }
        }
        
        //�V�j�@@�����Ώے������X�g�i�FArrayList�j�𐶐�����B 
        List l_lisLapseTargetOrder = new ArrayList();
        
        //�W�j�@@�����Ώے������m�肷��B 
        //�@@�R�j�̖߂�l(=�����Ώۂ̐敨OP�����P��List)�̗v�f�����ALoop�������s���A 
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_lisRows.get(i);
            for (int j = 0; j < l_lisOrderRequestNumbers.size(); j++) 
            {
                //�@@�����Ώۂ̗v�f.���ʃR�[�h�����ʃR�[�h���X�g�Ɋ܂܂��ꍇ�A 
                //�@@�����Ώے������X�g�ɏ����Ώۂ̗v�f��ǉ�����B
                if (l_ifoOrderUnitRow.getOrderRequestNumber().equals(l_lisOrderRequestNumbers.get(j)))
                {
                    l_lisLapseTargetOrder.add(l_ifoOrderUnitRow);
                }
            }
        }
        
        //�������Ώے������X�g.size() == 0�̏ꍇ�Anull��ԋp����B
        if (l_lisLapseTargetOrder.size() == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�X�j�@@�����Ώے������X�g.toArray()�̖߂�l��ԋp����B
        IfoOrderUnitRow[] l_rows = new IfoOrderUnitRow[l_lisLapseTargetOrder.size()];
        l_lisLapseTargetOrder.toArray(l_rows);
        log.exiting(STR_METHOD_NAME);
        return l_rows;

    }

    /**
     * (get�؋������ꗗ)<BR>
     * �����̃��N�G�X�g�f�[�^�ɂĎw�肳�ꂽ�����ɊY������ <BR>
     * �؋������̈ꗗ��ԋp����B <BR>
     * <BR>
     * �P�j�@@��������������i�FString�j�A���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B <BR>
     * <BR>
     * �Q�j �؋��������擾���錟���������쐬����B <BR>
     * <BR>
     * �Q�|�P�j�@@�،���ЃR�[�h���������������ɒǉ�����B <BR>
     * �@@�@@�������������� += " institution_code�@@= ? " <BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.�،����.�،���ЃR�[�h��ǉ�����B <BR>
     * <BR>
     * �Q�|�Q�j�@@���X���������������ɒǉ�����B <BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�ꗗ�̗v�f����"?"��ǉ�����B <BR>
     * <BR>
     * �@@�@@�������������� += " and branch_code in (?, ?,,,) " <BR>
     * <BR>
     * �@@�@@���X�����𐶐��������������f�[�^�R���e�i�ɒǉ�����B <BR>
     * �@@�@@�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f��S�ăf�[�^�R���e�i�ɒǉ�����B <BR>
     * <BR>
     * �Q�|�R�j �p�����[�^.���N�G�X�g�f�[�^�D�ڋq�R�[�h!= null�̏ꍇ�A<BR>
     * �@@�@@�@@�ڋq�R�[�h����������������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�������������� += "�@@and account_code�@@like ? " <BR>
     * <BR>
     * �f�[�^�R���e�i�Ɂu�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h�{%�v��ǉ�����B <BR>
     * <BR>
     * �Q�|�S�j�@@�������t�����������ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�������������� += " and calc_date = ?" <BR>
     * <BR>
     * �@@�@@�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�������t�ǉ�����B <BR>
     * <BR>
     * �R�j�@@DB����������B <BR>
     * �@@QueryProcessor.doFindAllQuery()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@arg0�F�@@�؋���Row.TYPE <BR>
     * �@@�@@arg1�F�@@�쐬������������������ <BR>
     * �@@�@@arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l <BR>
     * @@param l_institution - (�،����)<BR>
     * �،����
     * @@param l_request - (�Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g)<BR>
     * �Ǘ��ҁE�؋����s���󋵏Ɖ�N�G�X�g
     * @@return IfoDepositRow[]
     * @@throws WEB3BaseException
     */
    public static IfoDepositRow[] getDepositInfoList(
        Institution l_institution,
        WEB3AdminIfoDepShortageReferenceRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDepositInfoList(Institution, WEB3AdminIfoDepShortageReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        //�@@��������������i�FString�j�A���������f�[�^�R���e�i�i�FArrayList�j�𐶐�����B
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisContainers = new ArrayList();
        //�،���ЃR�[�h���������������ɒǉ�����B
        //�������������� += " institution_code�@@= ? "
        //�f�[�^�R���e�i�Ƀp�����[�^.�،����.�،���ЃR�[�h��ǉ�����B
        l_sbWhere.append(" institution_code = ? ");
        l_lisContainers.add(l_institution.getInstitutionCode());

        //���X���������������ɒǉ�����B
        //�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�ꗗ�̗v�f����"?"��ǉ�����B
        //�������������� += " and branch_code in (?, ?,,,) "
        //���X�����𐶐��������������f�[�^�R���e�i�ɒǉ�����B
        //�p�����[�^.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f��S�ăf�[�^�R���e�i�ɒǉ�����B
        int l_intBranchCodeLength = l_request.branchCode.length;
        l_sbWhere.append(" and branch_code in ( ");
        for (int i = 0; i < l_intBranchCodeLength; i++)
        {
            if (i + 1 == l_intBranchCodeLength)
            {
                l_sbWhere.append("?");
            }
            else
            {
                l_sbWhere.append("?, ");
            }
            l_lisContainers.add(l_request.branchCode[i]);
        }
        l_sbWhere.append(" ) ");

        //�p�����[�^.���N�G�X�g�f�[�^�D�ڋq�R�[�h!= null�̏ꍇ�A
        //�ڋq�R�[�h����������������ɒǉ�����B
        //�������������� += "�@@and account_code�@@like ? "
        //�f�[�^�R���e�i�Ɂu�p�����[�^.���N�G�X�g�f�[�^.�ڋq�R�[�h�{%�v��ǉ�����B
        if (l_request.accountCode != null)
        {
            l_sbWhere.append(" and account_code like ? || '%' ");
            l_lisContainers.add(l_request.accountCode);
        }

        //�������t�����������ɒǉ�����B
        //�������������� += " and calc_date = ?"
        //�f�[�^�R���e�i�Ƀp�����[�^.���N�G�X�g�f�[�^.�������t�ǉ�����B
        l_sbWhere.append(" and calc_date = ? ");
        l_lisContainers.add(l_request.searchDate);

        //QueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //arg0�F�@@�؋���Row.TYPE
        //arg1�F�@@�쐬������������������
        //arg2�F�@@�쐬�����f�[�^�R���e�i.toArray()�̖߂�l
        QueryProcessor l_ifoProcessor = null;
        List l_lisIfoDepositRows = null;
        Object[] l_ifoQueryContainers = new Object[l_lisContainers.size()];
        l_lisContainers.toArray(l_ifoQueryContainers);
        try
        {
            l_ifoProcessor = Processors.getDefaultProcessor();
            l_lisIfoDepositRows =
                l_ifoProcessor.doFindAllQuery(
                    IfoDepositRow.TYPE,
                    l_sbWhere.toString(),
                    l_ifoQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                "WEB3AdminIfoDataManager." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        IfoDepositRow[] l_ifoDepositRows = new IfoDepositRow[l_lisIfoDepositRows.size()];
        l_lisIfoDepositRows.toArray(l_ifoDepositRows);
        log.exiting(STR_METHOD_NAME);
        return l_ifoDepositRows;
    }

    /**
     * (is�؋����s�����[�����M��)<BR>
     * �Y����Е��X�̓��c�Ɠ��̏؋����s�����[�����M�ςł��邩�𔻒肷��B<BR>
     * �؋����s�����[�����M�ςł���ꍇ��true���A�����M�ł���ꍇ��false��ԋp����B<BR>
     * <BR>
     * �P�j�@@�v���Z�X�Ǘ�Params�̎擾<BR>
     * �@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�v���Z�X�Ǘ�Params( )<BR>
     * <BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�v���Z�XID�F�@@�h0001�h(�؋����s���m��)<BR>
     * �@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F�@@����.���X�R�[�h<BR>
     * <BR>
     * �Q�j�@@�؋����s�����[�����M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)�A<BR>
     * true��ԋp����B <BR>
     * <BR>
     * �R�j�@@�؋����s�����[�������M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))�A<BR>
     * false��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@return boolean
     */
    public static boolean isIfoDepositMailFlag(String l_strInstitutionCode, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "isIfoDepositMailFlag(String, String)";
        log.entering(STR_METHOD_NAME);

        //�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�v���Z�X�Ǘ�Params( )
        //[�����̐ݒ�]
        //�v���Z�XID�F�@@�h0001�h(�؋����s���m��)
        //�،���ЃR�[�h�F�@@����.�،���ЃR�[�h
        //���X�R�[�h�F�@@����.���X�R�[�h
        ProcessManagementParams l_processManagementParams =
            WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                WEB3ProcessIdDef.DEPOSIT_SHORTAGE_CONFIRM, l_strInstitutionCode, l_strBranchCode);

        //�؋����s�����[�����M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)�Atrue��ԋp����B
        if (l_processManagementParams != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //�؋����s�����[�������M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))�Afalse��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is���Z�l�����M��)<BR>
     * ���Z�l�����M�ς��ǂ����𔻒肷��B<BR>
     * <BR>
     * ��M�ςł���ꍇ��true���A��M�ςłȂ��ꍇ��false��ԋp����B<BR>
     * <BR>
     * �P�j�@@�v���Z�X�Ǘ�Params�̎擾<BR>
     * �@@�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�v���Z�X�Ǘ�Params( )<BR>
     * <BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�v���Z�XID�F�@@�h0008�h(���Z�l�����M)<BR>
     * �@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F�@@����.���X�R�[�h<BR>
     * <BR>
     * �Q�j�@@���Z�l�����M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)�Atrue��ԋp����B<BR>
     * <BR>
     * �R�j�@@���Z�l���񖢑��M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))�Afalse��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * @@return boolean
     */
    public static boolean isQuickReportReceived(String l_strInstitutionCode, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "isQuickReportReceived(String, String)";
        log.entering(STR_METHOD_NAME);

        //�؋����v�Z�f�[�^�\�[�X�A�N�Z�X�Ǘ�.get�v���Z�X�Ǘ�Params( )
        //[�����̐ݒ�]
        //�v���Z�XID�F�@@�h0008�h(���Z�l�����M)
        //�،���ЃR�[�h�F�@@����.�،���ЃR�[�h
        //���X�R�[�h�F�@@����.���X�R�[�h
        ProcessManagementParams l_processManagementParams =
            WEB3IfoDepositPersistentDataManager.getProcessManagementParams(
                WEB3ProcessIdDef.QUICK_REPORT_RECEIVED, l_strInstitutionCode, l_strBranchCode);

        //���Z�l�����M�ς̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^����)�Atrue��ԋp����B
        if (l_processManagementParams != null)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        //���Z�l���񖢑��M�̏ꍇ(get�v���Z�X�Ǘ�Params( )�ŊY���f�[�^�Ȃ�(null))�Afalse��ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
}
@
