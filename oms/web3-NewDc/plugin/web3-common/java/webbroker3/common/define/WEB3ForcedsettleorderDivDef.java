head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForcedsettleorderDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p�������ώ��{�敪�萔��`�C���^�t�F�C�X(WEB3ForcedsettleorderDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/25 �h�C(���u) �V�K�쐬
*/
package webbroker3.common.define;

/**
 * �M�p�������ώ��{�敪 �萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3ForcedsettleorderDivDef
{
    /**
     * 0�F�����{
     */
    public final static String NOT_ENFORCEMENT = "0";

    /**
     * 1�F���ϊ���������
     */
    public final static String SETTLEDAY_COME = "1";

    /**
     * 2�F�ۏ؋��ێ�������
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK = "2";

    /**
     * 3�F���ϊ����������{�ۏ؋��ێ�������
     */
    public final static String SETTLEDAY_COME_AND_GUARANTEE_MAINTENANCE_BREAK = "3";
}
@
