head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪�萔��`�C���^�t�F�C�X(WEB3AdminEquityStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 �����i���u�j�V�K�쐬
Revision History : 2009/02/13 �����i���u�j�c�a�X�V�d�lNo.025
*/
package webbroker3.common.define;

/**
 * �����敪�萔��`�C���^�t�F�C�X<BR>
 * (���ӏ��ʒm�L���[�e�[�u���̏����敪�̙ҏ�)<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public interface WEB3AdminEquityStatusDef
{
    /**
     * 0�F������<BR>
     */
    public final static String NOT_DEAL = "0";

    /**
     * 1�F������<BR>
     */
    public final static String DEALT = "1";

    /**
     * 3�F�d���f�[�^�i�����ΏۊO�j<BR>
     */
    public final static String REPEAT_ERROR = "3";

    /**
     * 9�F�G���[<BR>
     */
    public final static String ERROR = "9";
}
@
