head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExpirationStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����敪�C���^�t�F�C�X(WEB3IfoExpirationStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ������(���u)�@@�V�K�쐬
*/
package webbroker3.ifo.define;

/**
 * �����敪�@@�萔��`�C���^�t�F�C�X
 * 
 * @@author ������
 * @@version 1.0
 */
public interface WEB3IfoExpirationStatusDef
{
    /**
     * 0 �����Ȃ�
     */
    public static final String DEFAULT = "0";

    /**
     * 1 �ꕔ����
     */
    public static final String PARTIALLY_CLOSE = "1";

    /**
     * 2 �S������
     */
    public static final String ALL_CLOSE = "2";
}
@
