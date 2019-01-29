head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoOrderExecStateResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������󋵃��X�|���X(WEB3PvInfoOrderExecStateResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/20 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�������󋵃��X�|���X)<BR>
 * �������󋵃��X�|���X�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoOrderExecStateResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_orderExecState";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�c�Ɠ��̈ꗗ)<BR>
     * ���c�Ɠ��A���c�Ɠ��̏��Ŋi�[�B<BR>
     * ����������c�Ɠ��̏ꍇ�́A<BR>
     * �@@���c�Ɠ��A���X�c�Ɠ��̏��Ŋi�[�B<BR>
     */
    public Date[] bizDateList;
    
    /**
     * (�M�p��������J��)<BR>
     * �M�p����������J�݂��Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���J��<BR>
     * true�F�@@�J�ݍ�<BR>
     */
    public boolean marginAccOpen;
    
    /**
     * (�敨��������J��)<BR>
     * �敨����������J�݂��Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���J��<BR>
     * true�F�@@�J�ݍ�<BR>
     */
    public boolean futuresAccOpen;
    
    /**
     * (�I�v�V������������J��)<BR>
     * �I�v�V��������������J�݂��Ă��邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@���J��<BR>
     * true�F�@@�J�ݍ�<BR>
     */
    public boolean optionsAccOpen;
    
    /**
     * (������������)<BR>
     * ������������<BR>
     */
    public WEB3PvInfoTradeCountUnit orderCountsToday;
    
    /**
     * (������茏��)<BR>
     * ������茏��<BR>
     */
    public WEB3PvInfoTradeCountUnit execCountsToday;
    
    /**
     * (������������)<BR>
     * ������������<BR>
     */
    public WEB3PvInfoTradeCountUnit orderCountsTomorrow;
    
    /**
     * (��������������)
     * ����������<BR>
     */
    public WEB3PvInfoTradePriceUnit tradePriceUnit;
    
    /**
     * (IPO���I�������ꗗ[])<BR>
     * IPO���I�������ꗗ<BR>
     */
    public WEB3PvInfoIpoProductUnit[] ipoProductUnits;
    
    /**
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
     public WEB3PvInfoOrderExecStateResponse(WEB3GenRequest l_request)
     {
         super(l_request);
     }   
}
@
