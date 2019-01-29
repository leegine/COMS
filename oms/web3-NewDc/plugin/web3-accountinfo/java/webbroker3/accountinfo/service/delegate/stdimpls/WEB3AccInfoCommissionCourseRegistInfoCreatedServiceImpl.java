head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�XImpl(WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�XImpl)<BR>
 * ���q�l���ϑ��萔���R�[�X�ύX�\�����쐬�T�[�r�X�����N���X<BR>
 * 
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl implements WEB3AccInfoCommissionCourseRegistInfoCreatedService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl.class);
        
    /**
     * (create�萔���R�[�X�ύX�\�����)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g���A<BR>
     * �萔���R�[�X�ύX�\����񃁃b�Z�[�W�f�[�^���쐬����B<BR>
     * <BR>
     * �萔���R�[�X�ύX�\�����C���X�^���X�𐶐��A�ȉ��̒ʂ�v���p�e�B��<BR>
     * �Z�b�g���s���ԋp����B<BR>
     * <BR>
     * �@@�萔���R�[�X�ύX�\�����.�h�c = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.�ϑ��萔���R�[�X�ύX�\���h�c<BR>
     * �@@�萔���R�[�X�ύX�\�����.���X�R�[�h = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.get���X().getBranchCode()<BR>
     * �@@�萔���R�[�X�ύX�\�����.�ڋq�R�[�h = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.get�ڋq().get�\���ڋq�R�[�h()<BR>
     * �@@�萔���R�[�X�ύX�\�����.���i�R�[�h = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.get�萔�����i�R�[�h()<BR>
     * �@@�萔���R�[�X�ύX�\�����.�萔���R�[�X = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.get�萔���R�[�X�R�[�h()<BR>
     * �@@�萔���R�[�X�ύX�\�����.�\���� = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.get�\����()<BR>
     * �@@�萔���R�[�X�ύX�\�����.�K�p�J�n�� = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��()<BR>
     * �@@�萔���R�[�X�ύX�\�����.�_�E�����[�h�σt���O = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.is�_�E�����[�h��()<BR>
     * �@@�萔���R�[�X�ύX�\�����.����\�t���O = <BR>
     * �ϑ��萔���R�[�X�ύX�\��.is����\()<BR>
     * @@param l_commissionCourseRegist - �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo
     * @@roseuid 413E8ADD0063
     */
    public WEB3AccInfoCommissionCourseChangeInfo createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist l_commissionCourseRegist) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist)";
        log.entering(STR_METHOD_NAME);
        
        if (l_commissionCourseRegist == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_commissionCourseRegist] = " + l_commissionCourseRegist
                );
        }
        
        WEB3AccInfoCommissionCourseChangeInfo l_commissionCourseChangeInfo = new WEB3AccInfoCommissionCourseChangeInfo();
        
        CommissionCourseRegistParams l_params = (CommissionCourseRegistParams)l_commissionCourseRegist.getDataSourceObject();
        
        //�萔���R�[�X�ύX�\�����.�h�c = �ϑ��萔���R�[�X�ύX�\��.�ϑ��萔���R�[�X�ύX�\���h�c
        l_commissionCourseChangeInfo.id = "" + l_params.getCommissionCourseRegistId();
        
        //�萔���R�[�X�ύX�\�����.���X�R�[�h = �ϑ��萔���R�[�X�ύX�\��.get���X().getBranchCode()
        l_commissionCourseChangeInfo.branchCode = l_commissionCourseRegist.getBranch().getBranchCode();
        
        //�萔���R�[�X�ύX�\�����.�ڋq�R�[�h = �ϑ��萔���R�[�X�ύX�\��.get�ڋq().get�\���ڋq�R�[�h()
        l_commissionCourseChangeInfo.accountCode = l_commissionCourseRegist.getMainAccount().getDisplayAccountCode();
        
        //�萔���R�[�X�ύX�\�����.���i�R�[�h = �ϑ��萔���R�[�X�ύX�\��.get�萔�����i�R�[�h()
        l_commissionCourseChangeInfo.instrumentsCode = l_commissionCourseRegist.getCommissionProductCode();
        
        //�萔���R�[�X�ύX�\�����.�萔���R�[�X = �ϑ��萔���R�[�X�ύX�\��.get�萔���R�[�X�R�[�h()
        l_commissionCourseChangeInfo.commissionCourse = l_commissionCourseRegist.getCommissionCourseCode();
         
        //�萔���R�[�X�ύX�\�����.�\���� = �ϑ��萔���R�[�X�ύX�\��.get�\����()
        l_commissionCourseChangeInfo.applyDate = l_commissionCourseRegist.getRegistDate();
         
        //�萔���R�[�X�ύX�\�����.�K�p�J�n�� = �ϑ��萔���R�[�X�ύX�\��.get�K�p�J�n��()
        l_commissionCourseChangeInfo.trialStartDate = l_commissionCourseRegist.getAppliStartDate();
        
        //�萔���R�[�X�ύX�\�����.�_�E�����[�h�σt���O = �ϑ��萔���R�[�X�ύX�\��.is�_�E�����[�h��()
        l_commissionCourseChangeInfo.downloadFlag = l_commissionCourseRegist.isDownloaded();
         
        //�萔���R�[�X�ύX�\�����.����\�t���O = �ϑ��萔���R�[�X�ύX�\��.is����\()
        l_commissionCourseChangeInfo.cancelFlag = l_commissionCourseRegist.isCancelPossible();
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfo;
    }
    
    /**
     * (create�萔���R�[�X�ύX�\�����)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z����A<BR>
     * �萔���R�[�X�ύX�\����񃁃b�Z�[�W�f�[�^�̔z����쐬����B<BR>
     * <BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�z��e�s�ɂ��āA<BR>
     * this.create�萔���R�[�X�ύX�\�����()���R�[������B<BR>
     * <BR>
     * �@@[create�萔���R�[�X�ύX�\�����()�Ɏw�肷�����]<BR>
     * �@@�ϑ��萔���R�[�X�ύX�\���F�@@�i�����Ώۗv�f�j<BR>
     * <BR>
     * this.create�萔���R�[�X�ύX�\�����()�̕ԋp�l��z��ŕԂ��B<BR>
     * @@param l_commissionCourseRegists - �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z��
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 413E8FE2014D
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_commissionCourseRegists == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_commissionCourseRegists] = " + l_commissionCourseRegists
                );
        }
        
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = 
            new WEB3AccInfoCommissionCourseChangeInfo[l_commissionCourseRegists.length];
        
        for (int i = 0; i < l_commissionCourseRegists.length; i++) 
        {
            l_commissionCourseChangeInfoes[i] = createCommissionCourseRegistInfo(l_commissionCourseRegists[i]);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfoes;
    }
    
    /**
     * (create�����萔���R�[�X�ύX�\�����)<BR>
     * �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z����A<BR>
     * ��ꊔ���C�X�������̎萔���R�[�X�ύX�\����񃁃b�Z�[�W�f�[�^�̔z��<BR>
     * ���쐬����B<BR>
     * <BR>
     * �P�j�@@�萔���ύX�\�����ꗗList�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�萔���R�[�X�ύX�\����񐶐�<BR>
     * �@@�����̈ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�z��e�v�f�ɂ��āA<BR>
     * �Q�|�P�j�C�Q�|�Q�j�����{����B�@@<BR>
     * <BR>
     * �@@�Q�|�P�j�@@��ꊔ���I�u�W�F�N�g����<BR>
     * �@@�@@this.create�萔���R�[�X�ύX�\�����()���R�[������B<BR>
     * <BR>
     * �@@�@@[create�萔���R�[�X�ύX�\�����()�Ɏw�肷�����]<BR>
     * �@@�@@�ϑ��萔���R�[�X�ύX�\���F�@@�i�����Ώۗv�f�j<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�萔���ύX�\�����ꗗList�i�FArrayList�j�ɃI�u�W�F�N�g�ǉ�<BR>
     * �@@�@@�Q�|�P�j�Ő��������I�u�W�F�N�g���萔���ύX�\�����ꗗList<BR>
     * �i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �R�j�@@�z��ԋp<BR>
     * �@@�萔���ύX�\�����ꗗList�i�FArrayList�j��z��ɕϊ��itoArray()�j���A<BR>
     * �ԋp����B<BR>
     * @@param l_commissionCourseRegists - �ϑ��萔���R�[�X�ύX�\���I�u�W�F�N�g�̔z��
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommissionCourseChangeInfo[]
     * @@roseuid 41511ADC02FB
     */
    public WEB3AccInfoCommissionCourseChangeInfo[] createEquityCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createEquityCommissionCourseRegistInfo(WEB3AccInfoCommissionCourseRegist[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_commissionCourseRegists == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_commissionCourseRegists] = " + l_commissionCourseRegists
                );
        }
        
        List l_lisCommissionCourseChangeInfoes = new ArrayList();
        
        for (int i = 0; i < l_commissionCourseRegists.length; i++) 
        {
            //��ꊔ���I�u�W�F�N�g����
            WEB3AccInfoCommissionCourseChangeInfo l_commissionCourseChangeInfo = createCommissionCourseRegistInfo(l_commissionCourseRegists[i]);
            
            l_lisCommissionCourseChangeInfoes.add(l_commissionCourseChangeInfo);            
        }
        
        
        WEB3AccInfoCommissionCourseChangeInfo[] l_commissionCourseChangeInfoes = 
            new WEB3AccInfoCommissionCourseChangeInfo[l_lisCommissionCourseChangeInfoes.size()];            
        l_lisCommissionCourseChangeInfoes.toArray(l_commissionCourseChangeInfoes);
        
        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseChangeInfoes;
    }
}
@
