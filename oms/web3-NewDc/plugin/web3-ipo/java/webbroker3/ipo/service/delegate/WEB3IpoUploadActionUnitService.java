head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoUploadActionUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X(WEB3IpoUploadActionUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.ipo.message.WEB3IPOUploadHistoryUnit;

/**
 * (IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X)<BR>
 * IPO�A�b�v���[�h���𖾍׍쐬�T�[�r�X�C���^�t�F�C�X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public interface WEB3IpoUploadActionUnitService extends Service 
{
    
    /**
     * (create�A�b�v���[�h���𖾍�)<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s�I�u�W�F�N�g���A<BR>
     * IPO�A�b�v���[�h���𖾍׃I�u�W�F�N�g���쐬����B
     * @@throws WEB3BaseException
     * @@param l_administratorUploadParams - (�A�b�v���[�h�����s)<BR>
     * �A�b�v���[�h�����s�I�u�W�F�N�g<BR>
     * <BR>
     * ���@@�i�Ǘ��ҋ��ʁj�A�b�v���[�hParams�N���X��DDL�ɂĎ�������
     * @@return webbroker3.ipo.message.WEB3IpoUploadActionUnit
     * @@roseuid 40F227D40214
     */
    public WEB3IPOUploadHistoryUnit createUploadActionUnit(AdministratorUploadParams l_administratorUploadParams) throws WEB3BaseException;
}
@
