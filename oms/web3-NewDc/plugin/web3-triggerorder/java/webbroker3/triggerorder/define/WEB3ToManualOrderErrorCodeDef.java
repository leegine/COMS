head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.39.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToManualOrderErrorCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �蓮�����G���[�R�[�h�萔��`�C���^�t�F�C�X(WEB3ToManualOrderErrorCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 杊��] (���u)�@@�V�K�쐬
                 : 2006/08/24 ������ (���u)  ���� ���f��No.158
*/
package webbroker3.triggerorder.define;

/**
 * �蓮�����G���[�R�[�h�萔��`�C���^�t�F�C�X<BR>
 * 
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3ToManualOrderErrorCodeDef
{
    /**
     * 00�F�@@����
     */
    public static final String NORMAL = "00";

    /**
     * 01�F�@@����σG���[
     */
    public static final String CANCELED = "01";

    /**
     * 02�F�@@�����σG���[
     */
    public static final String ORDERED = "02";
    
    /**
     * 03�F�@@�������s
     */
    public static final String ORDER_FAILURE = "03";
    
    /**
     * 04�F�@@���σG���[ 
     */
    public static final String EXECUTED = "04";
    
    /**
     * 05�F�@@�����σG���[
     */
    public static final String EXPIREED = "05";

    /**
     * 90�F�@@�Y�������Ȃ�
     */
    public static final String NOT_AVAILABLE = "90";

    /**
     * 99�F�@@���̑��G���[
     */
    public static final String OTHER = "99";

}
@
