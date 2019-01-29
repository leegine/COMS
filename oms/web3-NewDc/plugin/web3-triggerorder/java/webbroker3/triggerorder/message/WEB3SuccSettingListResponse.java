head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccSettingListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���ݒ�ꗗ���X�|���X(WEB3SuccSettingListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�A���ݒ�ꗗ���X�|���X)<BR>
 * �A���ݒ�ꗗ���X�|���X�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccSettingListResponse extends WEB3GenResponse 
{
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200510111000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_settingList";
    
    /**
     * (�������ꗗ)<BR>
     * �������̈ꗗ<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (�������׈ꗗ)<BR>
     * �������׈ꗗ<BR>
     */
    public WEB3SuccOrderUnit[] orderUnitsList = null;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex = "0";
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages = "0";
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords = "0";
    
    /**
     * (�敨�������)<BR>
     * �敨�������<BR>
     * <BR>
     * ���N�G�X�g�̏��i�敪�ꗗ��"�敨"���܂܂��ꍇ�Z�b�g�B<BR>
     */
    public WEB3SuccProductInfo[] futuresProductInfo = null;
    
    /**
     * (�I�v�V�����������)<BR>
     * �I�v�V�����������<BR>
     * <BR>
     * ���N�G�X�g�̏��i�敪�ꗗ��"�I�v�V����"���܂܂��ꍇ�Z�b�g�B<BR>
     */
    public WEB3SuccProductInfo[] optionsProductInfo = null;
    
    /**
     * @@roseuid 43489603035B
     */
    public WEB3SuccSettingListResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3SuccSettingListResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }     
}
@
