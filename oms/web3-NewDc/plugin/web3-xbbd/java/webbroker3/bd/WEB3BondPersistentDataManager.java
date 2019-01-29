head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : ���f�[�^�}�l�[�W��(WEB3BondPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17 ����(���u) �V�K�쐬
 */

package webbroker3.bd;

import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondPositionManagerHelper.PersistentDataManager;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���f�[�^�}�l�[�W��)<BR>
 * ���f�[�^�}�l�[�W���N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3BondPersistentDataManager extends PersistentDataManager
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondPersistentDataManager.class);
    
    /**
     * @@roseuid 44E3531B0167
     */
    public WEB3BondPersistentDataManager() 
    {
     
    }
    
    /**
     * (get�ۗL���YParams)<BR>
     * getAsset�̃I�[�o�[���C�h<BR>
     * <BR>
     * �P�j���|�W�V�����}�l�[�W���[.get�ۗL���Y���Ă�<BR>
     * ����<BR>
     * BondFinTransactionParams.����ID<BR>
     * BondFinTransactionParams.�⏕����ID<BR>
     * BondFinTransactionParams.����ID<BR>
     * BondFinTransactionParams.�ŋ敪<BR>
     * <BR>
     * �Q�jAssetParams��Ԃ�<BR>
     * if(�߂�l�ۗ̕L���Y�I�u�W�F�N�g != null)<BR>
     * {<BR>
     *   �߂�l�ۗ̕L���Y�I�u�W�F�N�g.getDataSourceObject()��Ԃ�<BR>
     * }<BR>
     * else<BR>
     * {<BR>
     *   null��Ԃ�<BR>
     * }<BR>
     * @@param l_bondFinTransactionParams - BondFinTransactionParams<BR>
     * @@return AssetParams
     * @@throws WEB3BaseException 
     * @@roseuid 44D0362B005D
     */
    public AssetParams getAsset(
        BondFinTransactionParams l_bondFinTransactionParams)
    {
        final String STR_METHOD_NAME = " getAsset(BondFinTransactionParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondFinTransactionParams == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�p�����[�^�l��NULL");
        }
        //�P�j���|�W�V�����}�l�[�W���[.get�ۗL���Y���Ă� 
        //���� 
        //BondFinTransactionParams.����ID 
        //BondFinTransactionParams.�⏕����ID 
        //BondFinTransactionParams.����ID 
        //BondFinTransactionParams.�ŋ敪 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondPositionManager l_bondPositionManager = 
            (WEB3BondPositionManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_bondPositionManager.getAsset(
                l_bondFinTransactionParams.getAccountId(),
                l_bondFinTransactionParams.getSubAccountId(),
                l_bondFinTransactionParams.getProductId(),
                l_bondFinTransactionParams.getTaxType());
        }
        catch(WEB3BaseException l_ex)
        {
            log.error("__error in ���ۗL���Y�I�u�W�F�N�g�̎擾__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);   
        }
        
        //�Q�jAssetParams��Ԃ� 
        //if(�߂�l�ۗ̕L���Y�I�u�W�F�N�g != null) 
        if (l_asset != null)
        {
            //�߂�l�ۗ̕L���Y�I�u�W�F�N�g.getDataSourceObject()��Ԃ� 
            log.exiting(STR_METHOD_NAME);
            AssetRow l_assetRow = 
                (AssetRow)l_asset.getDataSourceObject();
            return new AssetParams(l_assetRow);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (set�X�V�ۗL���Y����)<BR>
     * setUpdateAssetAttributes�̃I�[�o�[���C�h�B<BR>
     * <BR>
     * �ۗL���YParams�̍X�V�Ώۂ̃v���p�e�B�ɁA�X�V�l���Z�b�g����B <BR>
     * �ivoid setUpdateAssetAttributes(AssetParams asset, Map vals)�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * �y�ۗL���Y�e�[�u���z�̍X�V�Ώۂ̍��ږ��ɁA<BR>
     * �ۗL���YParams�̓����v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@super.setUpdateAssetAttributes(�����ۗ̕L���YParams, �����̍X�V�lMap)���R�[�����A <BR>
     * �@@�@@�@@xTrade�W���v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �Q�j�@@�g���v���p�e�B���Z�b�g����B <BR>
     * <BR>
     * �@@�@@�@@-------------------------------------------------- <BR>
     * �@@�@@�@@���Z�b�g�Ώۍ��ځ� <BR>
     * <BR>
     * �@@�@@�@@���t�s�\���� <BR>
     * �@@�@@�@@���ʁi�뉿�P���v�Z�p�j <BR>
     * �@@�@@�@@-------------------------------------------------- <BR>
     * @@param l_assetParams - (�ۗL���YParams)<BR>
     * �ۗL���YParams<BR>
     * @@param l_mapUpdate - (�X�V�lMap)<BR>
     * �X�V�lMap<BR>
     * @@roseuid 44D992BB02DE
     */
    public void setUpdateAssetAttributes(AssetParams l_assetParams, Map l_mapUpdate) 
    {
        final String STR_METHOD_NAME = " setUpdateAssetAttributes(AssetParams, Map)";
        log.entering(STR_METHOD_NAME);
        
        if (l_assetParams == null || l_mapUpdate == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "�p�����[�^�l��NULL");
        }
        
        //�P�j�@@super.setUpdateAssetAttributes(�����ۗ̕L���YParams, �����̍X�V�lMap)���R�[�����A  
        //  xTrade�W���v���p�e�B���Z�b�g����B  
        super.setUpdateAssetAttributes(l_assetParams, l_mapUpdate);
        
        //�Q�j�@@�g���v���p�e�B���Z�b�g����B  
        //
        //  --------------------------------------------------  
        //  ���Z�b�g�Ώۍ��ځ�  
        //
        //  ���t�s�\����  
        //  ���ʁi�뉿�P���v�Z�p�j  
        //  --------------------------------------------------  
        l_mapUpdate.put("quantity_cannot_sell", new Double(l_assetParams.getQuantityCannotSell()));
        l_mapUpdate.put("quantity_for_book_value", new Double(l_assetParams.getQuantityForBookValue()));
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
