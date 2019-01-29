head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoUploadActionUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X�����N���X(WEB3IPOUploadHistoryUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>053
*/

package webbroker3.ipo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.ipo.define.WEB3LotDivDef;
import webbroker3.ipo.define.WEB3UploadStateDivDef;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X�����N���X
 *                                                              
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3IpoUploadActionUnitServiceImpl implements WEB3IpoUploadActionUnitService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IpoUploadActionUnitServiceImpl.class);        
    
    /**
     * @@roseuid 4112F191020B
     */
    public WEB3IpoUploadActionUnitServiceImpl() 
    {
     
    }
    
    /**
     * (create�A�b�v���[�h���𖾍�)<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s�I�u�W�F�N�g���A<BR>
     * IPO�A�b�v���[�h���𖾍׃I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * IPO�A�b�v���[�h���𖾍׃I�u�W�F�N�g�𐶐����A<BR>
     * �ȉ��̒ʂ�v���p�e�B���Z�b�g���ĕԋp����B<BR>
     * <BR>
     * ���@@�����I�i�A�b�v���[�h�����s == null�j�̏ꍇ�A<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.���I�敪 = �h�V�K���I�h<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.�A�b�v���[�h��� = �h�A�b�v���[�h�҂��h<BR>
     * �@@�i�ȊO�Anull�j<BR>
     * <BR>
     * ���@@���I�ρi�A�b�v���[�h�����s != null�j�̏ꍇ�A<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.���I�敪 = �A�b�v���[�h�����s.���l�P<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.�A�b�v���[�h������ԋ敪 = <BR>
     * �@@�@@�i�A�b�v���[�h�����s.�A�b�v���[�h�I������ == null�j�̏ꍇ�A<BR>
     * �h�A�b�v���[�h���h<BR>
     * �@@�@@�i�A�b�v���[�h�����s.�A�b�v���[�h�I������ != null�j�̏ꍇ�A<BR>
     * �h�A�b�v���[�h�ρh<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.�A�b�v���[�h���� = <BR>
     * �A�b�v���[�h�����s.�A�b�v���[�h����<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.�A�b�v���[�h�J�n���� = <BR>
     * �A�b�v���[�h�����s.�A�b�v���[�h�J�n����<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.�A�b�v���[�h�I������ = <BR>
     * �A�b�v���[�h�����s.�A�b�v���[�h�I������<BR>
     * �@@IPO�A�b�v���[�h���𖾍�.IPO�G���[�ԍ� = <BR>
     * �A�b�v���[�h�����s.���b�Z�[�W�R�[�h<BR>
     * @@throws WEB3BaseException
     * @@param l_administratorUploadParams - (�A�b�v���[�h�����s)<BR>
     * �A�b�v���[�h�����s�I�u�W�F�N�g<BR>
     * <BR>
     * ���@@�i�Ǘ��ҋ��ʁj�A�b�v���[�hParams�N���X��DDL�ɂĎ�������<BR>
     * @@return webbroker3.ipo.message.WEB3IpoUploadActionUnit
     * @@roseuid 40F224BE00AD
     */
    public WEB3IPOUploadHistoryUnit createUploadActionUnit(AdministratorUploadParams l_administratorUploadParams) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createUploadActionUnit(AdministratorUploadParams)";
        log.entering(STR_METHOD_NAME);
        
        WEB3IPOUploadHistoryUnit l_ipoUploadHistoryUnit = 
            new WEB3IPOUploadHistoryUnit();
        if(l_administratorUploadParams == null)
        {
            //���I�敪
            l_ipoUploadHistoryUnit.lotDiv = WEB3LotDivDef.OPEN_LOTTERY;
            
            //�A�b�v���[�h��� 
            l_ipoUploadHistoryUnit.uploadStateDiv = WEB3UploadStateDivDef.DEFAULT;
            
            
            //* �A�b�v���[�h����
            l_ipoUploadHistoryUnit.uploadNumber = null;
    
            //* �A�b�v���[�h�J�n����
            l_ipoUploadHistoryUnit.uploadStartDate = null;

            //* �A�b�v���[�h�I������
            l_ipoUploadHistoryUnit.uploadEndDate = null;
    
            //* IPO�G���[�ԍ�<BR>
            l_ipoUploadHistoryUnit.ipoErrorId = null;
        }
        else
        {
            //���I�敪
            l_ipoUploadHistoryUnit.lotDiv = l_administratorUploadParams.getNote1();
            
            //�A�b�v���[�h������ԋ敪
            if(l_administratorUploadParams.getUploadEndTimestamp() == null)
            {
                l_ipoUploadHistoryUnit.uploadStateDiv = WEB3UploadStateDivDef.UPLOAD_STATUS_PROCESS;
            }
            else
            {
                l_ipoUploadHistoryUnit.uploadStateDiv = WEB3UploadStateDivDef.UPLOAD_STATUS_COMPLETE;
            }
            
            //�A�b�v���[�h����
            l_ipoUploadHistoryUnit.uploadNumber = Integer.toString(l_administratorUploadParams.getUploadCount());
            
            //�A�b�v���[�h�J�n����
            l_ipoUploadHistoryUnit.uploadStartDate = l_administratorUploadParams.getUploadStartTimestamp();
            
            //�A�b�v���[�h�I������
            l_ipoUploadHistoryUnit.uploadEndDate = l_administratorUploadParams.getUploadEndTimestamp();
            
            //IPO�G���[�ԍ�
            l_ipoUploadHistoryUnit.ipoErrorId = l_administratorUploadParams.getMessageCode();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_ipoUploadHistoryUnit;
    }
}
@
