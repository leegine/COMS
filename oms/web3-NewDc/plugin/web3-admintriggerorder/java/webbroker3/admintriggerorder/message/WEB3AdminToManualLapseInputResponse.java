head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToManualLapseInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�蓮�������̓��X�|���X(WEB3AdminToManualLapseInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20�@@�]�V�q(���u) �V�K�쐬
*/

package webbroker3.admintriggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�g���K�[�����Ǘ��ҁE�蓮�������̓��X�|���X)<BR>
 * �g���K�[�����Ǘ��ҁE�蓮�������̓��X�|���X<BR>
 * 
 * @@author �]�V�q
 * @@version 1.0
 */
public class WEB3AdminToManualLapseInputResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_to_manual_lapse_input";
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603161700L;
    
    /**
     * (����������ʈꗗ)<BR>
     * ����������ʈꗗ<BR>
     * <BR>
     * 1�F�@@�A������<BR>
     * 2�F�@@OCO����<BR>
     * 3�F�@@IFD����<BR>
     * 4�F�@@�t�w�l����<BR>
     * 5�F�@@W�w�l����<BR>
     * <BR>
     * ��AP�ł͏����������{���ǂ����𔻕ʂ�����@@��<BR>
     * �@@���݂��Ȃ��ׁA�ꗥnull���Z�b�g����B<BR>
     * �@@�i���̍��ڂ͏����I�ɑΉ������ꍇ�Ɏg�p����B�j<BR>
     */
    public String[] triggerOrderTypeList = null;
    
    /**
     * (���i�敪�ꗗ)<BR>
     * ���i�敪�ꗗ<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V���� <BR>
     */
    public String[] productDivList;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�ꗗ<BR>
     */
    public String[] marketList = null;
    
    /**
     * @@roseuid 44192EEB036B
     */
    public WEB3AdminToManualLapseInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminToManualLapseInputResponse(WEB3AdminToManualLapseInputRequest l_request)
    {
        super(l_request);
    } 
}
@
