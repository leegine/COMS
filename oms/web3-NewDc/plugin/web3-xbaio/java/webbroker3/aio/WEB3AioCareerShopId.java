head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCareerShopId.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�����A�ʉ����XID�N���X(WEB3AioCareerShopId.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/13 ���� (���u)  �V�K�쐬
                   2006/04/26 WeiXin (���u) �d�l�ύX�E���f��542
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.aio.data.CareerShopIdDao;
import webbroker3.aio.data.CareerShopIdRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;


/**
 * (�L�����A�ʉ����XID)<BR>
 * �L�����A�ʉ����XID�N���X�B
 * 
 * @@author ���� 
 * @@version 1.0
 */
public class WEB3AioCareerShopId 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */ 
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AioCareerShopId.class);      
    
    /**
     * (�L�����A�ʉ����XIDRow)<BR>
     * �L�����A�ʉ����XID�s�I�u�W�F�N�g�B
     */
    private CareerShopIdRow  careerShopIdRow;
    
    /**
     * @@roseuid 443DDF5E035B
     */
    public WEB3AioCareerShopId() 
    {
     
    }
    
    /**
     * (�L�����A�ʉ����XID)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �������L�[�Ƃ��āA�L�����A�ʉ����X�h�c�e�[�u���̃��R�[�h��<BR>
     * �擾���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strCareerDiv - (�L�����A�敪)
     * @@return webbroker3.aio.WEB3AioCareerShopId
     * @@throws NotFoundException 
     * @@throws WEB3BaseException 
     * @@roseuid 443511BE015C
     */
    public WEB3AioCareerShopId(String l_strInstitutionCode, String l_strBranchCode, String l_strCareerDiv) 
        throws NotFoundException, WEB3BaseException 
    {
        final String STR_METHOD_NAME = " WEB3AioCareerShopId(String, String, String)";
        log.entering(STR_METHOD_NAME);
        try
        {
            careerShopIdRow = CareerShopIdDao.findRowByPk(
                l_strInstitutionCode,
                l_strBranchCode, 
                l_strCareerDiv);
                    
        }
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("�������ʂɈ�v����s�����݂��Ȃ�");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
 
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�����XID)<BR>
     * �����XID���擾����B
     * @@return String
     * @@roseuid 443528140215
     */
    public String getShopId() 
    {
        return careerShopIdRow.getShopId();
    }
    
    /**
     * (get����URL)<BR>
     * ����URL���擾����B
     * @@return String
     * @@roseuid 4435282F0215
     */
    public String getPfURL() 
    {
        return careerShopIdRow.getPfUrl();
    }
    
    /**
     * (get���^�[��URL )<BR>
     * ���^�[��URL���擾����B
     * @@return String
     * @@roseuid 4435282F0215
     */
    public String getReturnURL() 
    {
        return careerShopIdRow.getReturnUrl();
    }

}
@
